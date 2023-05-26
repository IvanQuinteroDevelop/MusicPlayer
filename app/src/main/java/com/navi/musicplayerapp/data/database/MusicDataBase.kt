package com.navi.musicplayerapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.navi.musicplayerapp.domain.entity.TrackEntity

@Database(entities = [TrackEntity::class], version = 1)
abstract class MusicDataBase: RoomDatabase() {
    abstract fun MusicDao(): MusicDao
}