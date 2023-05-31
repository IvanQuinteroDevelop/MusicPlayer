package com.navi.musicplayerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusicPlayerApp: Application() {
    companion object {
        lateinit var instance: MusicPlayerApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}