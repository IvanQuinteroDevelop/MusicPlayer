package com.navi.musicplayerapp.data.apiservices

import com.navi.musicplayerapp.data.responses.GenreApiResponse
import com.navi.musicplayerapp.data.responses.TracksApiResponse
import retrofit2.http.GET

interface ApiMusicServices {

    @GET("chart")
    suspend fun getTracks(): TracksApiResponse

    @GET("genre")
    suspend fun getGenres(): GenreApiResponse
}