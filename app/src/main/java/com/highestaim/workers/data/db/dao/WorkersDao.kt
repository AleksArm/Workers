package com.highestaim.workers.data.db.dao

import androidx.room.*
import com.highestaim.workers.data.db.entity.WorkerEntity
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface WorkersDao {

    @Query("SELECT * from workers ")
    fun getWorkers(): Single<List<WorkerEntity?>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(workers: List<WorkerEntity?>)

    @Insert
    fun insert(worker: WorkerEntity?): Completable

    @Query("UPDATE workers SET name = :name WHERE id = :id")
    fun updateName(name: String?, id: String): Completable


    @Query("UPDATE workers SET employee_salary = :salary WHERE id = :id")
    fun updateSalary(salary: String?, id: String): Completable


    @Query("UPDATE workers SET employee_age = :age WHERE id = :id")
    fun updateAge(age: String?, id: String): Completable

    @Update
    fun updateWorkers(workers: List<WorkerEntity?>): Completable

}