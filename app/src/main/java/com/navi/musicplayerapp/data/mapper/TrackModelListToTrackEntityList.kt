package com.navi.musicplayerapp.data.mapper

import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.model.TrackModel

fun List<TrackModel>.toTrackEntityList(): List<TrackEntity> {
    return this.map { trackModel ->
        TrackEntity(
            id = trackModel.id,
            title = trackModel.title,
            duration = trackModel.duration,
            preview = trackModel.preview,
            artistName = trackModel.artist.name,
            cover = trackModel.album.cover
        )
    }
}