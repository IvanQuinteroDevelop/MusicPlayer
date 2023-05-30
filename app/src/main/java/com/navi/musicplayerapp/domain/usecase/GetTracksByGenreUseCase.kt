package com.navi.musicplayerapp.domain.usecase

import com.navi.musicplayerapp.data.repository.MusicRepository
import javax.inject.Inject

class GetTracksByGenreUseCase @Inject constructor(private val repository: MusicRepository) {
    suspend operator fun invoke(genreId: String) = repository.getTracksByGenreId(genreId)
}