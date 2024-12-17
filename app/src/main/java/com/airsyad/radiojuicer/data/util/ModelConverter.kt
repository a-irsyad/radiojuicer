package com.airsyad.radiojuicer.data.util

import com.airsyad.radiojuicer.data.Station
import com.airsyad.radiojuicer.data.local.FavouriteStation

fun Station.toFavouriteStation() = FavouriteStation(
    stationUiid = stationUiid,
    name = name,
    logoUrl = logoUrl
)

fun FavouriteStation.toStation() = Station(
    stationUiid = stationUiid,
    name = name,
    logoUrl = logoUrl
)