package com.navi.musicplayerapp.domain.usecase

import com.navi.musicplayerapp.data.repository.MusicRepository
import com.navi.musicplayerapp.domain.model.TrackModel
import javax.inject.Inject

class RemoveFavoriteTrackUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(trackModel: TrackModel) = musicRepository.removeFromFavorite(trackModel)
}