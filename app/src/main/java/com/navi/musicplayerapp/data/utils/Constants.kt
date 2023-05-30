package com.navi.musicplayerapp.data.utils

object Constants {
    const val BASE_URL = "https://api.deezer.com/"
    const val TRENDING_SONGS = "chart"
    const val GENRES = "genre"
    const val ARTIST_BY_GENRE_ID = "genre/{id}/artists"
    const val TRACKS_BY_ARTIST_ID = "artist/{id}/top?limit=1"
}