package com.airsyad.radiojuicer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model class representing a favorite radio station entity for the Room database.
 *
 * This class is used solely for database-related operations. If other layers or components need access
 * to a favorite station, it should be converted to the internal model [Station].
 */
@Entity(tableName = "favourite_radio_station")
data class FavouriteStation(
    @PrimaryKey val stationUiid: String,
    val name: String,
    val logoUrl: String
)