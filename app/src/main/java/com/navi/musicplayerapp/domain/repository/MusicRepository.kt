package com.navi.musicplayerapp.domain.repository

import com.navi.musicplayerapp.data.apiservices.ApiMusicServices
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.model.TrackModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class MusicRepository @Inject constructor(private val apiMusicServices: ApiMusicServices) {

    suspend fun getTracks(): ApiResponseStatus<List<TrackModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val tracks = apiMusicServices.getArtist().data
                ApiResponseStatus.Success(tracks)
            } catch (e: Exception) {
                ApiResponseStatus.Error("Error to load data")
            } catch (e: UnknownHostException) {
                ApiResponseStatus.Error("Error connection")
            }
        }
    }
}