package com.navi.musicplayerapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.model.TrackModel
import com.navi.musicplayerapp.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val addTrackUseCase: AddTrackUseCase,
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
    private val removeFavoriteTrackUseCase: RemoveFavoriteTrackUseCase,
    private val getTracksByGenreUseCase: GetTracksByGenreUseCase,
) : ViewModel() {

    private val _tracksStatus =
        MutableStateFlow<ApiResponseStatus<Any>>(ApiResponseStatus.Loading())
    val tracksStatus = _tracksStatus.asStateFlow()

    private val _genresStatus =
        MutableStateFlow<ApiResponseStatus<Any>>(ApiResponseStatus.Loading())
    val genresStatus = _genresStatus.asStateFlow()

    private val _tracksByGenreStatus =
        MutableStateFlow<ApiResponseStatus<Any>>(ApiResponseStatus.Loading())
    val tracksByGenreStatus = _tracksByGenreStatus.asStateFlow()

    private val _favoriteTracks = MutableLiveData<List<TrackEntity>>()
    val favoriteTracks: LiveData<List<TrackEntity>> = _favoriteTracks

    init {
        _tracksStatus.value = ApiResponseStatus.Loading()
        getTrackList()
        getGenres()
        getFavoriteList()
    }


    private fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.invoke().also { _genresStatus.value = it }
        }
    }

    private fun getTrackList() {
        viewModelScope.launch {
            (getTracksUseCase.invoke()).also { _tracksStatus.value = it }
        }
    }

    fun getTracksByGenre(genreId: String) {
        viewModelScope.launch {
            _tracksByGenreStatus.value = ApiResponseStatus.Loading()
            (getTracksByGenreUseCase.invoke(genreId)).also { _tracksByGenreStatus.value = it }
        }
    }

    fun addFavoriteTrack(trackModel: TrackModel) {
        viewModelScope.launch {
            addTrackUseCase.invoke(trackModel)
        }
    }

    fun removeFavoriteTrack(trackModel: TrackModel) {
        viewModelScope.launch {
            removeFavoriteTrackUseCase.invoke(trackModel)
        }
    }

    private fun getFavoriteList() {
        viewModelScope.launch {
            _favoriteTracks.value = getFavoriteTracksUseCase.invoke()
        }
    }
}