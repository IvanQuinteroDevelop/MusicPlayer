package com.navi.musicplayerapp.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.usecase.GetTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(private val getTracksUseCase: GetTracksUseCase): ViewModel() {

    var status = mutableStateOf<ApiResponseStatus<Any>?>(null)
        private set

    init {
        // val randomArtist = (1 until 99).random()
        status.value = ApiResponseStatus.Loading()
        getTrackList()
    }

    private fun getTrackList() {
        viewModelScope.launch {
            status.value = getTracksUseCase.invoke() as ApiResponseStatus<Any>
        }
    }
}