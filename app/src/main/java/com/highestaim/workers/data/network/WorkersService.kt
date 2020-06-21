package com.highestaim.workers.data.network

import com.highestaim.workers.model.WorkersModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface WorkersService {

    @GET("employees")
    fun getWorkers() : Flowable<WorkersModel?>

    companion object {
        fun create(): WorkersService {
            return RetroClass.retroInstance.create(WorkersService::class.java)
        }
    }

}