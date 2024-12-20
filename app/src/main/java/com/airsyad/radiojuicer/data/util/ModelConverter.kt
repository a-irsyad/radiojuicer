package com.airsyad.radiojuicer.data.util

import com.airsyad.radiojuicer.data.Country
import com.airsyad.radiojuicer.data.Station
import com.airsyad.radiojuicer.data.local.FavouriteStation
import com.r.cohen.radiobrowserandroid.models.RadioBrowserCountry
import com.r.cohen.radiobrowserandroid.models.RadioBrowserState
import com.r.cohen.radiobrowserandroid.models.RadioBrowserStation

/**
 * Convert [Station] (internal model) to [FavouriteStation] (persistence model.
 */
fun Station.toFavouriteStation() = FavouriteStation(
    stationUiid = stationUiid,
    name = name,
    logoUrl = logoUrl
)

/**
 * Convert [FavouriteStation] (persistence model) to [Station] (internal model).
 */
fun FavouriteStation.toStation() = Station(
    stationUiid = stationUiid,
    name = name,
    logoUrl = logoUrl
)

/**
 * Convert [RadioBrowserStation] (DTO) to [Station] (internal model)
 */
fun RadioBrowserStation.toStation() = Station(
    stationUiid = stationuuid,
    name = name,
    logoUrl = favicon
)

/**
 * Convert [RadioBrowserCountry] (DTO) to [Country] (internal model).
 **/
fun RadioBrowserCountry.toCountry() = Country(
    name = name,
    countryCode = iso_3166_1,
    stationCount = stationcount
)

/**
 * Convert [Country] (internal model) to [RadioBrowserCountry] (DTO).
 **/
fun Country.toRadioBrowserCountry() = RadioBrowserCountry(
    name = name,
    iso_3166_1 = countryCode,
    stationcount = stationCount
)

