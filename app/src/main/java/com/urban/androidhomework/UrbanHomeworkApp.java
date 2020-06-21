package com.urban.androidhomework;

import android.app.Application;

public class UrbanHomeworkApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkClient.get().setup(getApplicationContext());
    }
}
