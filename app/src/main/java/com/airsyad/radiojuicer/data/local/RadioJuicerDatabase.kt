package com.airsyad.radiojuicer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavouriteStation::class],
    exportSchema = false,
    version = 1
)
abstract class RadioJuicerDatabase : RoomDatabase() {
    abstract fun favouriteRadioStationDao(): FavouriteStationDao
}