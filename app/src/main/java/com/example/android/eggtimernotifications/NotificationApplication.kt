package com.example.android.eggtimernotifications

import android.app.Application
import timber.log.Timber

class NotificationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}