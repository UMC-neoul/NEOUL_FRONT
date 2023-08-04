package com.example.neoul

import android.app.Application
import android.content.SharedPreferences
import com.example.neoul.di.appModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NeoulApplication: Application() {

    val API_URL = "http://43.200.23.72:8080"

    companion object{
        lateinit var sSharedPreference:SharedPreferences

        const val X_ACCESS_TOKEN = "accessToken"

        lateinit var sRetrofit: Retrofit

    }


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                Level.NONE
            )
            androidContext(this@NeoulApplication)
            modules(appModule)
        }

        sSharedPreference = applicationContext.getSharedPreferences("", MODE_PRIVATE)

        initRetrofitInstance()
    }

    private fun initRetrofitInstance(){
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    }




}