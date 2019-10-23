package com.app.jcs.application

import android.app.Application
import com.app.jcs.di.modules.authViewModule
import com.app.jcs.di.modules.mainViewModule
import com.app.jcs.di.modules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(retrofitModule, authViewModule, mainViewModule))
        }
    }
}