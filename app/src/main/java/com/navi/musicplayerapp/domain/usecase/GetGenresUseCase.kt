package com.navi.musicplayerapp.domain.usecase

import com.navi.musicplayerapp.data.repository.MusicRepository
import com.navi.musicplayerapp.data.responses.ApiResponseStatus
import com.navi.musicplayerapp.domain.model.Genre
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(): ApiResponseStatus<List<Genre>> = musicRepository.getGenres()
}