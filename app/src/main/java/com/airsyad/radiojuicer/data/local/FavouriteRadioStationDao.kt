package com.airsyad.radiojuicer.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteRadioStationDao {
    @Query("SELECT * FROM favourite_radio_station")
    fun getAll(): Flow<List<FavouriteRadioStation>>

    @Upsert
    fun upsert(favouriteRadioStation: FavouriteRadioStation)

    @Query("DELETE FROM favourite_radio_station")
    fun deleteAll()
}