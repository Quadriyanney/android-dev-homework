package com.urban.androidhomework

import android.app.Application
import com.urban.androidhomework.di.component.DaggerAppComponent

class UrbanHomeworkApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}