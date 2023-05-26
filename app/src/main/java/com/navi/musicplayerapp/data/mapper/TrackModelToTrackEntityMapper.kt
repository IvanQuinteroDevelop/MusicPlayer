package com.navi.musicplayerapp.data.mapper

import com.navi.musicplayerapp.domain.entity.TrackEntity
import com.navi.musicplayerapp.domain.model.TrackModel

fun TrackModel.toTrackEntity() = TrackEntity(
    id,
    title,
    duration,
    preview,
    artist.name,
    album.cover
)