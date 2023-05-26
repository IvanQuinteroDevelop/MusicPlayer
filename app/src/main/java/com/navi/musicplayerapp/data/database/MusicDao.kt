package com.navi.musicplayerapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.navi.musicplayerapp.domain.entity.TrackEntity

@Dao
interface MusicDao {

    @Insert
    suspend fun addFavoriteTrack(item: TrackEntity)

    @Delete
    suspend fun removeFavoriteTrack(item: TrackEntity)

    @Query("SELECT * from TrackEntity")
    suspend fun getFavoriteTracks(): List<TrackEntity>
}