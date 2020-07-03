package com.urban.androidhomework.di.component

import com.urban.androidhomework.UrbanHomeworkApp
import com.urban.androidhomework.data.di.DataModule
import com.urban.androidhomework.di.module.AppModule
import com.urban.androidhomework.di.module.ThreadExecutionModule
import com.urban.androidhomework.remote.di.NetworkDependenciesModule
import com.urban.androidhomework.remote.di.RemoteModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        ThreadExecutionModule::class,
        DataModule::class,
        NetworkDependenciesModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: UrbanHomeworkApp): Builder

        fun build(): AppComponent
    }
}