package com.airsyad.radiojuicer.data.repository

import com.airsyad.radiojuicer.data.Country
import com.airsyad.radiojuicer.data.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {

    fun getAllFavouriteStations(): Flow<List<Station>>

    suspend fun upsertFavouriteStation(station: Station)

    suspend fun deleteAllFavouriteStations()

    suspend fun fetchCountries(): List<Country>

    suspend fun fetchStationsByCountry(countryCode: String, offset: Int, limit: Int): List<Station>

    suspend fun fetchStationByName(name: String, offset: Int, limit: Int): List<Station>

    suspend fun registerStationClick(stationUiid: String)
}