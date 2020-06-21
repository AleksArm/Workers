package com.highestaim.workers.viewmodel

import androidx.lifecycle.ViewModel
import com.highestaim.workers.di.DaggerAppComponent
import com.highestaim.workers.repository.WorkersRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WorkersUpdateViewModel : ViewModel() {

    @Inject
    lateinit var repository: WorkersRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
    }

     fun updateWorkerName(name : String?,workerId: String) {
         repository.updateWorkerName(name,workerId)
    }

    fun updateWorkerSalary(salary : String?,workerId: String) {
        repository.updateWorkerSalary(salary,workerId)
    }

    fun updateWorkerAge(age : String?,workerId: String) {
        repository.updateWorkerAge(age,workerId)
    }

    fun updateCachedData() {
        compositeDisposable.add(repository.updateCachedData())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}