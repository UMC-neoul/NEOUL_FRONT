package com.example.neoul

import android.app.Application
import com.example.neoul.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NeoulApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                Level.NONE
            )
            androidContext(this@NeoulApplication)
            modules(appModule)
        }
    }
}