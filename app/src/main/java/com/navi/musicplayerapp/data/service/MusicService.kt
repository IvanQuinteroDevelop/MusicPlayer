package com.navi.musicplayerapp.data.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.navi.musicplayerapp.data.notification.notificationMusic
import com.navi.musicplayerapp.data.utils.Constants.ACTION_NEXT
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PAUSE
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PLAY
import com.navi.musicplayerapp.data.utils.Constants.ACTION_PREVIOUS
import com.navi.musicplayerapp.data.utils.Constants.ACTION_RESUME
import com.navi.musicplayerapp.data.utils.Constants.NOTIFICATION_ID
import com.navi.musicplayerapp.domain.entity.TrackEntity

class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private val binder = ServiceBinder()
    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayPosition = 0
    private var isPaused = false

    // alternative
    private val currentTrack =
        TrackEntity(30L, "redemption", 40, "preview of track", "Muse", "cover of track")

    inner class ServiceBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            ACTION_PLAY -> {
                playTrack(currentTrack.preview)
                startNotification(currentTrack, true)
            }
            ACTION_PAUSE -> pauseTrack()
            ACTION_NEXT,
            ACTION_PREVIOUS -> changeTrack(currentTrack)
            ACTION_RESUME -> resumeTrack()
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    init {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioAttributes(
            AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA).build()
        )
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer?.start()
    }

    private fun startNotification(track: TrackEntity, isPlaying: Boolean) {
        val notification =
            notificationMusic(this, track, application, isPlaying)
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun playTrack(preview: String) {
        mediaPlayer?.apply {
            if (isPlaying && !isPaused) {
                isPaused = false
                setDataSource(preview)
                setOnCompletionListener {
                    // TODO() sendBroadcast()
                }
                setOnPreparedListener(this@MusicService)
                prepareAsync()
            } else {
                resumeTrack()
            }
        }
    }

    private fun resumeTrack() {
        mediaPlayer?.apply {
            if (!isPlaying) {
                isPaused = false
                seekTo(currentPlayPosition)
                start()
                startNotification(currentTrack, isPaused)
            }
        }
    }

    private fun changeTrack(track: TrackEntity) {
        mediaPlayer?.apply {
            stop()
            reset()
            setDataSource(track.preview)
            prepareAsync()
            setOnCompletionListener {
                // TODO() sendBroadcast()
            }
            setOnPreparedListener(this@MusicService)
            startNotification(track, true)
        }
    }

    private fun pauseTrack() {
        mediaPlayer?.apply {
            if (isPlaying) {
                isPaused = true
                currentPlayPosition = currentPosition
                pause()
                startNotification(currentTrack, !isPaused)
            }
        }
    }
}