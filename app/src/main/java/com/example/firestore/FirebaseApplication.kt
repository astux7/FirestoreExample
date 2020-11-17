package com.example.firestore

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FirebaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FirebaseApplication)
            modules(CuteModule.module)
        }
    }
}