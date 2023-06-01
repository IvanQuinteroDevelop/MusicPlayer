package com.navi.musicplayerapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.player.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicPlayer: MusicPlayer
): ViewModel() {

    private val _currentTracks = MutableStateFlow<List<TrackEntity?>>(musicPlayer.getCurrentTrackList())

    private val _currentTrack = MutableStateFlow<TrackEntity?>(_currentTracks.value.firstOrNull())
    val currentTrack: StateFlow<TrackEntity?> = _currentTrack

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    private val _isPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> = _isPaused


    init {
        musicPlayer.startPlayer()
        _currentTrack.value = _currentTracks.value.firstOrNull()
    }

    fun playTrack(trackEntity: TrackEntity) {
        _currentTrack.value = trackEntity
        musicPlayer.startTrack(trackEntity.id.toString())
        updateStatePlaying(true)
    }

    private fun updateStatePlaying(state: Boolean) {
        _isPlaying.value = state
    }

    fun setTracks(tracks: List<TrackEntity>) {
        musicPlayer.setTracks(tracks)
        _currentTracks.value = tracks
        _currentTrack.value = _currentTracks.value.first()
    }

    fun resumeTrack() {
        musicPlayer.resumeTrack()
        updateStatePlaying(true)
    }

    fun pauseTrack() {
        musicPlayer.pauseTrack()
        updateStatePlaying(false)
        _isPaused.value = true
    }

    fun nextTrack() {
        musicPlayer.nextTrack()
        updateCurrentTrack()
        updateStatePlaying(true)
    }

    fun updateCurrentTrack() {
        _currentTrack.value = musicPlayer.getCurrentTrack()
    }

    fun previousTrack() {
        musicPlayer.previousTrack()
        updateCurrentTrack()
        updateStatePlaying(true)
    }
}