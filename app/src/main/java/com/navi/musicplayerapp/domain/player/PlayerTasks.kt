package com.navi.musicplayerapp.domain.player

import com.navi.musicplayerapp.domain.entity.TrackEntity

interface PlayerTasks {
    fun startPlayer()
    fun setTracks(tracks: List<TrackEntity?>)
    fun startTrack(id: String)
    fun getTrackById(id: String): TrackEntity?
    fun getNextTrack(track: TrackEntity?): TrackEntity?
    fun getPreviousTrack(track: TrackEntity?): TrackEntity?
    fun resumeTrack()
    fun pauseTrack()
    fun nextTrack()
    fun previousTrack()
    fun stopPlayer()
}