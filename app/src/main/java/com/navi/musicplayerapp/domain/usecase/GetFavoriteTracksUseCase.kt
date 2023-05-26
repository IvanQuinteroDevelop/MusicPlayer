package com.navi.musicplayerapp.domain.usecase

import com.navi.musicplayerapp.data.repository.MusicRepository
import com.navi.musicplayerapp.domain.entity.TrackEntity
import javax.inject.Inject

class GetFavoriteTracksUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(): List<TrackEntity> = musicRepository.getFavoriteTracks()
}