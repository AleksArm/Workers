package com.highestaim.workers.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workers")
data class WorkerEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "employee_salary")
    var salary: String?,

    @ColumnInfo(name = "employee_age")
    var age: String?
)