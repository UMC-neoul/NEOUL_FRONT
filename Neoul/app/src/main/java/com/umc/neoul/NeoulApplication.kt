package com.umc.neoul

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.umc.neoul.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class NeoulApplication: Application() {

    companion object{
        lateinit var sSharedPreference:SharedPreferences
    }
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //KakaoSdk.init(this,"d333b474c3a293707baf0b7c410922c4")

        startKoin {
            androidLogger(
                Level.NONE
            )
            androidContext(this@NeoulApplication)
            modules(appModule)
        }

        com.umc.neoul.NeoulApplication.Companion.sSharedPreference =
            applicationContext.getSharedPreferences("NEOUL_APP", MODE_PRIVATE)



    }


}