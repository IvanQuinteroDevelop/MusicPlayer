package com.navi.musicplayerapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.usecase.GetGenresUseCase
import com.navi.musicplayerapp.domain.usecase.GetTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _tracksStatus =
        MutableStateFlow<ApiResponseStatus<Any>>(ApiResponseStatus.Loading())
    val tracksStatus = _tracksStatus.asStateFlow()

    private val _genresStatus =
        MutableStateFlow<ApiResponseStatus<Any>>(ApiResponseStatus.Loading())
    val genresStatus = _genresStatus.asStateFlow()

    init {
        _tracksStatus.value = ApiResponseStatus.Loading()
        getTrackList()
        getGenres()
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
}