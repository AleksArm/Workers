package com.highestaim.workers.di

import com.highestaim.workers.di.modules.AppModule
import com.highestaim.workers.di.modules.RepositoriesModule
import com.highestaim.workers.di.modules.ServicesModule
import com.highestaim.workers.repository.WorkersRepository
import com.highestaim.workers.ui.MainActivity
import com.highestaim.workers.viewmodel.WorkersUpdateViewModel
import com.highestaim.workers.viewmodel.WorkersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,ServicesModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(workerRepository: WorkersRepository)

    fun inject(viewModel: WorkersViewModel)

    fun inject(viewModel: WorkersUpdateViewModel)

    fun inject(mainActivity: MainActivity)
}