package com.navi.musicplayerapp.domain.usecase

import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.model.TrackModel
import com.navi.musicplayerapp.domain.repository.MusicRepository
import javax.inject.Inject

class GetTracksUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(): ApiResponseStatus<List<TrackModel>> = musicRepository.getTracks()
}