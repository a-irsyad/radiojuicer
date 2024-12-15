package com.airsyad.radiojuicer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteRadioStation::class],
    exportSchema = false,
    version = 1
)
abstract class RadioStationDatabase : RoomDatabase() {
    abstract fun favouriteRadioStationDao(): FavouriteRadioStationDao
}