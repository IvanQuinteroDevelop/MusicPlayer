package com.navi.musicplayerapp.data.utils

object Constants {
    const val BASE_URL = "https://api.deezer.com/"
    const val TRENDING_SONGS = "chart"
    const val GENRES = "genre"
    const val ARTIST_BY_GENRE_ID = "genre/{id}/artists"
    const val TRACKS_BY_ARTIST_ID = "artist/{id}/top?limit=1"

    const val ACTION_PLAY = "com.action.play"
    const val ACTION_PAUSE = "com.action.pause"
    const val ACTION_NEXT = "com.action.next"
    const val ACTION_PREVIOUS = "com.action.previous"
    const val ACTION_RESUME = "com.action.resume"
    const val EXTRA_TRACK = "track_extra"

    @JvmField val MUSIC_NOTIFICATION_CHANNEL_NAME: CharSequence = "Notification Music"
    const val CHANNEL_ID = "music_channel"
    const val NOTIFICATION_ID = 7
}