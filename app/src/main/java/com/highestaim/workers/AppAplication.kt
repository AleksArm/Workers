package com.highestaim.workers

import android.app.Application
import com.highestaim.workers.data.db.AppDatabase

class AppApplication : Application() {

    companion object {
        lateinit var instance: AppApplication
        lateinit var database: AppDatabase
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}