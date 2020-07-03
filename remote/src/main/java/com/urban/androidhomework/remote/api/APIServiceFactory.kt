package com.urban.androidhomework.remote.api

import io.reactivex.schedulers.Schedulers
import ng.softcom.remote.BuildConfig
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIServiceFactory {

    fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    fun makeDispatcher(): Dispatcher {
        return Dispatcher().apply {
            maxRequestsPerHost = 10
        }
    }

    fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)

        return httpClientBuilder.build()
    }

    fun makeRetrofit(okHttpClient: OkHttpClient, apiURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiURL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun makeService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}
