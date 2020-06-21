package com.highestaim.workers.di.modules

import com.highestaim.workers.repository.WorkersRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideWorkersRepository() = WorkersRepository()
}