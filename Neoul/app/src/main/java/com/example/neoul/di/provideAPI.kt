package com.example.neoul.di

import com.example.neoul.data.network.BrandApiService
import com.example.neoul.data.network.ProductApiService
import com.example.neoul.data.network.StoryApiService
import com.example.neoul.data.network.Url
import com.example.neoul.data.repository.story.StoryRepository
import com.example.neoul.presentation.user.login.LoginDataSource.LoginInterface
import com.example.neoul.presentation.user.signup.SignupDataFile.SignupInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideStoryApiService(retrofit: Retrofit): StoryApiService {
    return retrofit.create(StoryApiService::class.java)
}

fun provideBrandApiService(retrofit: Retrofit): BrandApiService {
    return retrofit.create(BrandApiService::class.java)
}

fun provideLoginApiService(retrofit: Retrofit):LoginInterface{
    return retrofit.create(LoginInterface::class.java)
}

fun provideSignUpApiService(retrofit: Retrofit):SignupInterface{
    return retrofit.create(SignupInterface::class.java)
}


fun provideProductApiService(retrofit: Retrofit): ProductApiService {
    return retrofit.create(ProductApiService::class.java)
}

fun provideNeoulRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.NEOUL_SERVER)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun providerGsonConvertFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

fun buildOkHttpClint(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
      //  .addInterceptor(interceptor)
        .build()
}