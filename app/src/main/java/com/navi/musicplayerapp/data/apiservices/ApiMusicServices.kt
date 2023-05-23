package com.navi.musicplayerapp.data.apiservices

import com.navi.musicplayerapp.data.responses.TrackApiResponse
import retrofit2.http.GET

interface ApiMusicServices {

    @GET("artist/52/top?limit=20")
    suspend fun getArtist(): TrackApiResponse
}