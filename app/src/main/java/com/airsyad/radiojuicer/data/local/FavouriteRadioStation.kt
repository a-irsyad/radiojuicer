package com.airsyad.radiojuicer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_radio_station")
data class FavouriteRadioStation(
    @PrimaryKey val stationUiid: String,
    val name: String,
    val logoUrl: String
)