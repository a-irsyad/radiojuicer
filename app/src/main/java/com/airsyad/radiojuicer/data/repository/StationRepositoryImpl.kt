package com.airsyad.radiojuicer.data.repository

import com.airsyad.radiojuicer.data.Country
import com.airsyad.radiojuicer.data.Station
import com.airsyad.radiojuicer.data.local.FavouriteStationDao
import com.airsyad.radiojuicer.data.util.NetworkResult
import com.airsyad.radiojuicer.data.util.toCountry
import com.airsyad.radiojuicer.data.util.toFavouriteStation
import com.airsyad.radiojuicer.data.util.toStation
import com.r.cohen.radiobrowserandroid.RadioBrowserApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Singleton
class StationRepositoryImpl @Inject constructor(
    private val favouriteStationDao: FavouriteStationDao,
    private val radioBrowserApi: RadioBrowserApi
) : StationRepository {

    override fun getAllFavouriteStations(): Flow<List<Station>> {
        return favouriteStationDao.getAll().map { favouriteStations ->
            favouriteStations.map { it.toStation() }
        }
    }

    override suspend fun upsertFavouriteStation(station: Station) {
        favouriteStationDao.upsert(station.toFavouriteStation())
    }

    override suspend fun deleteAllFavouriteStations() {
        favouriteStationDao.deleteAll()
    }

    override suspend fun fetchCountries(): Flow<NetworkResult<List<Country>>> = flow {
        emit(NetworkResult.Loading)
        try {
            // Use suspendCoroutine to convert the callback-based API to a suspending function
            val radioBrowserCountries = suspendCoroutine { continuation ->
                radioBrowserApi.getCountries(
                    onSuccess = { countries -> continuation.resume(countries) },
                    onFail = { errorMsg -> continuation.resumeWithException(Exception(errorMsg)) }
                )
            }
            emit(NetworkResult.Success(radioBrowserCountries.map { it.toCountry() }))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

    override suspend fun fetchStationsByCountry(
        countryCode: String,
        offset: Int,
        limit: Int
    ): Flow<NetworkResult<List<Station>>> = flow{
        emit(NetworkResult.Loading)
        try {
            val radioBrowserStations = suspendCoroutine { continuation ->
                radioBrowserApi.getStationsByCountry(
                    countryCode = countryCode,
                    onSuccess = {stations -> continuation.resume(stations)},
                    onFail = {errorMsg -> continuation.resumeWithException(Exception(errorMsg))}
                )
            }
            emit(NetworkResult.Success(radioBrowserStations.map{it.toStation()}))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }

    override suspend fun fetchStationsByName(
        name: String,
        offset: Int,
        limit: Int
    ): Flow<NetworkResult<List<Station>>> = flow {
        emit(NetworkResult.Loading)
        try{
            val radioBrowserStations = suspendCoroutine{ continuation ->
                radioBrowserApi.searchStationsByName(
                    search = name,
                    onSuccess = {stations -> continuation.resume(stations)},
                    onFail = {errorMsg -> continuation.resumeWithException(Exception(errorMsg))}
                )
            }
            emit(NetworkResult.Success(radioBrowserStations.map{it.toStation()}))
        }catch(e: Exception){
            emit(NetworkResult.Error(e))
        }
    }

    override suspend fun registerStationClick(stationUiid: String) {
        radioBrowserApi.stationClick(
            stationUuid = stationUiid,
            onSuccess = {},
            onFail = {}
        )
    }
}