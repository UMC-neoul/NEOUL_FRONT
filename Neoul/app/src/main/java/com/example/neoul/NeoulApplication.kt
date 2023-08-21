package com.example.neoul

import android.app.Application
import android.content.SharedPreferences
import com.example.neoul.di.appModule
import com.kakao.sdk.common.KakaoSdk
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

        KakaoSdk.init(this,"d333b474c3a293707baf0b7c410922c4")

        startKoin {
            androidLogger(
                Level.NONE
            )
            androidContext(this@NeoulApplication)
            modules(appModule)
        }

        sSharedPreference =
            applicationContext.getSharedPreferences("NEOUL_APP", MODE_PRIVATE)



    }


}