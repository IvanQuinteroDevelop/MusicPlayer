package com.navi.musicplayerapp.data.apiservices

import com.navi.musicplayerapp.data.responses.ArtistsResponse
import com.navi.musicplayerapp.data.responses.GenreApiResponse
import com.navi.musicplayerapp.data.responses.TracksApiResponse
import com.navi.musicplayerapp.data.responses.TracksResponse
import com.navi.musicplayerapp.data.utils.Constants.ARTIST_BY_GENRE_ID
import com.navi.musicplayerapp.data.utils.Constants.GENRES
import com.navi.musicplayerapp.data.utils.Constants.TRACKS_BY_ARTIST_ID
import com.navi.musicplayerapp.data.utils.Constants.TRENDING_SONGS
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiMusicServices {

    @GET(TRENDING_SONGS)
    suspend fun getTracks(): TracksApiResponse

    @GET(GENRES)
    suspend fun getGenres(): GenreApiResponse

    @GET(ARTIST_BY_GENRE_ID)
    suspend fun getArtistByGenre(@Path("id") id: String): ArtistsResponse

    @GET(TRACKS_BY_ARTIST_ID)
    suspend fun getTracksByArtist(@Path("id") artistId: String): TracksResponse
}