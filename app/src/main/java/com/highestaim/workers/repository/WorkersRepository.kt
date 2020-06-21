package com.highestaim.workers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.highestaim.workers.AppApplication
import com.highestaim.workers.di.DaggerAppComponent
import com.highestaim.workers.data.db.entity.WorkerEntity
import com.highestaim.workers.data.db.toDataEntityList
import com.highestaim.workers.data.db.toDataList
import com.highestaim.workers.data.network.WorkersService
import com.highestaim.workers.model.WorkersModel
import com.highestaim.workers.util.Utils.isOnline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject


class WorkersRepository {

    @Inject
    lateinit var workersService: WorkersService


    private val workersMutableLiveData by lazy { MutableLiveData<Result<List<WorkersModel.Data?>?>>() }

    val data: LiveData<Result<List<WorkersModel.Data?>?>>
        get() = workersMutableLiveData


    init {
        DaggerAppComponent.create().inject(this)
    }

    fun updateCachedData(): Disposable {
        return workersService.getWorkers()
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabaseForUpdate())
    }


    private fun subscribeToDatabaseForUpdate(): DisposableSubscriber<WorkersModel?> {
        return object : DisposableSubscriber<WorkersModel?>() {

            override fun onNext(trendingResult: WorkersModel?) {

                val workersEntities = trendingResult?.data?.toDataEntityList()
                updateWorkers(workersEntities)
            }

            override fun onError(t: Throwable?) {
            }

            override fun onComplete() {
            }
        }
    }

    private fun insertAllDataToDb(): Disposable {
        return workersService.getWorkers()
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<WorkersModel?> {
        return object : DisposableSubscriber<WorkersModel?>() {

            override fun onNext(trendingResult: WorkersModel?) {

                val workersEntities = trendingResult?.data?.toDataEntityList()
                AppApplication.database.apply {
                    workersEntities?.let { workersDao().insertAll(it) }
                }
            }

            override fun onError(t: Throwable?) {
                t?.fillInStackTrace()?.let {
                    workersMutableLiveData.postValue(Result.failure(it))
                }
            }

            override fun onComplete() {
                getWorkersFromDb()
            }
        }
    }


    private fun getWorkersFromDb(): Disposable {
        return AppApplication.database.workersDao()
            .getWorkers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataEntityList ->
                    if (dataEntityList != null && dataEntityList.isNotEmpty()) {
                        workersMutableLiveData.value = Result.success(dataEntityList.toDataList())
                    } else {
                        if (isOnline(AppApplication.instance).first) {
                            insertAllDataToDb()
                        } else {
                            workersMutableLiveData.value = Result.success(null)
                        }
                    }
                },
                {
                    workersMutableLiveData.value = Result.failure(it.fillInStackTrace())
                }
            )
    }

    fun updateWorkerName(name: String?, workerId: String) {
        name?.let {
            AppApplication.database.workersDao()
                .updateName(name, workerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            println("Name is update")
                            getWorkersFromDb()
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        }
    }

    fun updateWorkerSalary(salary: String?, workerId: String) {
        salary?.let {
            AppApplication.database.workersDao()
                .updateSalary(salary, workerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            println("Salary is updated!")
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        }
    }

    fun updateWorkerAge(age: String?, workerId: String) {
        age?.let {
            AppApplication.database.workersDao()
                .updateAge(age, workerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : DisposableCompletableObserver() {
                        override fun onComplete() {
                            println("Age is update")
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        }
    }

    private fun updateWorkers(workers: List<WorkerEntity?>?) {
        workers?.let {
            AppApplication.database.workersDao()
                .updateWorkers(workers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    object : DisposableCompletableObserver() {
                        override fun onComplete() {
                        }

                        override fun onError(e: Throwable) {
                            e.printStackTrace()
                        }
                    })
        }
    }

    fun fetchDataFromDatabase(): Disposable = getWorkersFromDb()

}