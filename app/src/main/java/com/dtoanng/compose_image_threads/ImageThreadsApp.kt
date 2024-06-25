package com.dtoanng.compose_image_threads

import android.app.Application
import timber.log.Timber
import com.dtoanng.compose_image_threads.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImageThreadsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}