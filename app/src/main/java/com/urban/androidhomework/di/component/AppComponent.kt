package com.urban.androidhomework.di.component

import com.urban.androidhomework.UrbanHomeworkApp
import com.urban.androidhomework.data.di.DataModule
import com.urban.androidhomework.di.component.scopes.AppScope
import com.urban.androidhomework.di.component.ui.UIComponent
import com.urban.androidhomework.di.module.AppModule
import com.urban.androidhomework.di.module.SubComponentModule
import com.urban.androidhomework.di.module.UtilsModule
import com.urban.androidhomework.remote.di.NetworkDependenciesModule
import com.urban.androidhomework.remote.di.RemoteModule
import dagger.BindsInstance
import dagger.Component

/**
 * Component for the Application Lifecycle
 */
@AppScope
@Component(
    modules = [
        AppModule::class,
        UtilsModule::class,
        DataModule::class,
        RemoteModule::class,
        SubComponentModule::class,
        NetworkDependenciesModule::class
    ]
)
interface AppComponent {

    fun uiComponent(): UIComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: UrbanHomeworkApp): Builder

        fun build(): AppComponent
    }
}