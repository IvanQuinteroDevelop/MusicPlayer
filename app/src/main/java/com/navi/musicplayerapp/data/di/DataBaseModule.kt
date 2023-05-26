package com.navi.musicplayerapp.data.di

import android.content.Context
import androidx.room.Room
import com.navi.musicplayerapp.data.database.MusicDao
import com.navi.musicplayerapp.data.database.MusicDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): MusicDataBase {
        return Room.databaseBuilder(appContext, MusicDataBase::class.java, "MusicDataBase").build()
    }

    @Provides
    fun provideDao(musicDataBase: MusicDataBase): MusicDao = musicDataBase.MusicDao()
}