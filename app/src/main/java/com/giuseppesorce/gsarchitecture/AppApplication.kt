package com.giuseppesorce.gsarchitecture

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author Giuseppe Sorce
 */

@HiltAndroidApp
class AppApplication : Application() {



    override fun onCreate() {
        super.onCreate()
//        FirebaseApp.initializeApp(this)
        Timber.plant(Timber.DebugTree())

    }
}