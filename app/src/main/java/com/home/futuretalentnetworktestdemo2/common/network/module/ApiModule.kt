package com.home.futuretalentnetworktestdemo2.common.network.module

import com.home.futuretalentnetworktestdemo2.common.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(Api::class.java)
    }
}
