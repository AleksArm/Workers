package com.highestaim.workers.di.modules

import com.highestaim.workers.data.network.WorkersService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServicesModule {

    @Singleton
    @Provides
    fun provideWorkerApi(): WorkersService = WorkersService.create()
}