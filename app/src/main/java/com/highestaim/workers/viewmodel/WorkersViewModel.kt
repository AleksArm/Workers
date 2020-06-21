package com.highestaim.workers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.highestaim.workers.di.DaggerAppComponent
import com.highestaim.workers.model.WorkersModel
import com.highestaim.workers.repository.WorkersRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WorkersViewModel : ViewModel() {

    @Inject
    lateinit var repository: WorkersRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun getDataFromDb() : LiveData<Result<List<WorkersModel.Data?>?>> {
        compositeDisposable.add(repository.fetchDataFromDatabase())
        return repository.data
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}