package com.airsyad.radiojuicer.di

import android.content.Context
import androidx.room.Room
import com.airsyad.radiojuicer.data.local.FavouriteStationDao
import com.airsyad.radiojuicer.data.local.RadioJuicerDatabase
import com.r.cohen.radiobrowserandroid.RadioBrowserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModules {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RadioJuicerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RadioJuicerDatabase::class.java,
            "RadioJuicerDatabase.db"
        ).build()
    }

    @Provides
    fun provideFavouriteStationDao(radioJuicerDatabase: RadioJuicerDatabase): FavouriteStationDao {
        return radioJuicerDatabase.favouriteRadioStationDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
object RadioBrowserApiModule {

    @Singleton
    @Provides
    fun provideRadioBrowserApi(): RadioBrowserApi = RadioBrowserApi()

}