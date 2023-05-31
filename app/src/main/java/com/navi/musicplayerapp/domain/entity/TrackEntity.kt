package com.navi.musicplayerapp.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TrackEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val duration: Int,
    val preview: String,
    val artistName: String,
    val cover: String
): Parcelable
