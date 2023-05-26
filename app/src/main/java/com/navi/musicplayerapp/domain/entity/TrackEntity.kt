package com.navi.musicplayerapp.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val duration: Int,
    val preview: String,
    val artistName: String,
    val cover: String
)
