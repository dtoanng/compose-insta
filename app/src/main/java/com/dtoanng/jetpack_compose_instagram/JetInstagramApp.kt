package com.dtoanng.jetpack_compose_instagram

import android.app.Application
import timber.log.Timber
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JetInstagramApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}