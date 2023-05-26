package com.navi.musicplayerapp.data.repository

import com.navi.musicplayerapp.R
import com.navi.musicplayerapp.data.apiservices.ApiMusicServices
import com.navi.musicplayerapp.data.database.MusicDao
import com.navi.musicplayerapp.data.mapper.toTrackEntity
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.model.Genre
import com.navi.musicplayerapp.domain.model.TrackModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val apiMusicServices: ApiMusicServices,
    private val dispatcher: CoroutineDispatcher,
    private val musicDao: MusicDao
) {

    suspend fun getTracks(): ApiResponseStatus<List<TrackModel>> {
        return withContext(dispatcher) {
            try {
                val tracks = apiMusicServices.getTracks().tracks.data
                ApiResponseStatus.Success(tracks)
            } catch (e: Exception) {
                ApiResponseStatus.Error(R.string.error_loading_data)
            } catch (e: UnknownHostException) {
                ApiResponseStatus.Error(R.string.error_connection)
            }
        }
    }

    suspend fun getGenres(): ApiResponseStatus<List<Genre>> {
        return withContext(dispatcher) {
            try {
                val genres = apiMusicServices.getGenres().data
                ApiResponseStatus.Success(genres)
            } catch (e: Exception) {
                ApiResponseStatus.Error(R.string.error_loading_data)
            } catch (e: UnknownHostException) {
                ApiResponseStatus.Error(R.string.error_connection)
            }
        }
    }

    suspend fun addToFavorite(trackModel: TrackModel) {
        musicDao.addFavoriteTrack(trackModel.toTrackEntity())
    }

    suspend fun removeFromFavorite(trackModel: TrackModel) = musicDao.removeFavoriteTrack(trackModel.toTrackEntity())

    suspend fun getFavoriteTracks(): List<TrackEntity> = musicDao.getFavoriteTracks()

}