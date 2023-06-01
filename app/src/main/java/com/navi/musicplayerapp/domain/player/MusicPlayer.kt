package com.navi.musicplayerapp.domain.player

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.navi.musicplayerapp.data.service.MusicService
import com.navi.musicplayerapp.data.utils.Constants
import com.navi.musicplayerapp.domain.entity.TrackEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicPlayer @Inject constructor(private val context: Context) : PlayerTasks {

    private var currentTrack: TrackEntity? = null
    private var tracks: List<TrackEntity?> = emptyList()
    private var musicService: MusicService? = null
    private var serviceBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceBound = true
            val binder = service as MusicService.ServiceBinder
            musicService = binder.getService()
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBound = false
            musicService = null
        }
    }

    override fun startPlayer() {
        val serviceIntent = Intent(context, MusicService::class.java)
        context.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        context.startService(serviceIntent)
    }

    override fun setTracks(tracks: List<TrackEntity?>) {
        this.tracks = tracks
    }

    override fun startTrack(id: String) {
        currentTrack = getTrackById(id)
        if (currentTrack != null && serviceBound) {
            val intent = createPlayerIntent(Constants.ACTION_PLAY)
            context.startService(intent)
        }
    }

    override fun resumeTrack() {
        if (currentTrack != null && serviceBound) {
            val intent = createPlayerIntent(Constants.ACTION_RESUME)
            context.startService(intent)
        }
    }

    override fun pauseTrack() {
        if (serviceBound) {
            val intent = createPlayerIntent(Constants.ACTION_PAUSE)
            context.startService(intent)
        }
    }

    override fun nextTrack() {
        val nextTrack = getNextTrack(currentTrack)
        if (nextTrack != null && serviceBound) {
            currentTrack = nextTrack
            val intent = createPlayerIntent(Constants.ACTION_NEXT)
            context.startService(intent)
        }
    }

    override fun previousTrack() {
        val previousTrack = getPreviousTrack(currentTrack)
        if (previousTrack != null && serviceBound) {
            currentTrack = previousTrack
            val intent = createPlayerIntent(Constants.ACTION_PREVIOUS)
            context.startService(intent)
        }
    }

    override fun getPreviousTrack(track: TrackEntity?): TrackEntity? {
        val currentIndex = tracks.indexOf(track)
        if (tracks.isNotEmpty()) {
            return if (currentIndex > 0) {
                tracks[currentIndex - 1]
            } else {
                tracks.lastOrNull()
            }
        }
        return null
    }

    override fun getTrackById(id: String): TrackEntity? {
        return tracks.find { it?.id.toString() == id }
    }

    override fun getNextTrack(track: TrackEntity?): TrackEntity? {
        val currentIndex = tracks.indexOf(track)
        if (tracks.isNotEmpty()) {
            val nextIndex = (currentIndex + 1) % tracks.size
            return tracks[nextIndex]
        }
        return null
    }

    override fun stopPlayer() {
        if (serviceBound) {
            val intent = Intent(context, MusicService::class.java)
            context.stopService(intent)
            context.unbindService(serviceConnection)
            serviceBound = false
        }
    }

    private fun createPlayerIntent(action: String): Intent {
        return Intent(context, MusicService::class.java).apply {
            this.action = action
            putExtra(Constants.EXTRA_TRACK, currentTrack)
        }
    }

    fun getCurrentTrack() = currentTrack

    fun getCurrentTrackList() = tracks
}