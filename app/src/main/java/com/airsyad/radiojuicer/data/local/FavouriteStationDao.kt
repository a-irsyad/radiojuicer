package com.airsyad.radiojuicer.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteStationDao {
    @Query("SELECT * FROM favourite_radio_station")
    fun getAll(): Flow<List<FavouriteStation>>

    @Upsert
    fun upsert(favouriteStation: FavouriteStation)

    @Query("DELETE FROM favourite_radio_station")
    fun deleteAll()
}