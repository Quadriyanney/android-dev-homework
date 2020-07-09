package com.urban.androidhomework.remote.di

import com.urban.androidhomework.remote.api.APIServiceFactory
import com.urban.androidhomework.remote.api.CharacterService
import com.urban.androidhomework.remote.api.LocationService
import dagger.Module
import dagger.Provides
import ng.softcom.remote.BuildConfig
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Provide Network Dependencies
 */
@Module
object NetworkDependenciesModule {

    @Provides
    fun provideDispatcher(): Dispatcher {
        return APIServiceFactory.makeDispatcher()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return APIServiceFactory.makeLoggingInterceptor()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ): OkHttpClient {
        return APIServiceFactory.makeOkHttpClient(httpLoggingInterceptor, dispatcher)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return APIServiceFactory.makeRetrofit(okHttpClient, BuildConfig.API_URL)
    }

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService {
        return APIServiceFactory.makeService(retrofit)
    }

    @Provides
    fun provideLocationService(retrofit: Retrofit): LocationService {
        return APIServiceFactory.makeService(retrofit)
    }
}
