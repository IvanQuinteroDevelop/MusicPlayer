package com.navi.musicplayerapp.data.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.navi.musicplayerapp.data.utils.Constants
import com.navi.musicplayerapp.domain.player.MusicPlayer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BroadcastMusicReceiver: BroadcastReceiver() {

    @Inject
    lateinit var musicPlayer: MusicPlayer

    override fun onReceive(context: Context?, intent: Intent?) {
        musicPlayer.let {
            when (intent?.action) {
                Constants.ACTION_RESUME,
                Constants.ACTION_PLAY -> it.resumeTrack()
                Constants.ACTION_PAUSE -> it.pauseTrack()
                Constants.ACTION_NEXT -> it.nextTrack()
                Constants.ACTION_PREVIOUS -> it.previousTrack()
            }
        }
    }
}