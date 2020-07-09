package com.urban.androidhomework

import android.app.Application
import com.urban.androidhomework.di.component.AppComponent
import com.urban.androidhomework.di.component.DaggerAppComponent

class UrbanHomeworkApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}
