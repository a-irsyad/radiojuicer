package com.airsyad.radiojuicer.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class FavouriteRadioStationDaoTest {
    private lateinit var database: RadioJuicerDatabase
    private lateinit var dao: FavouriteRadioStationDao

    private val favouriteRadioStation1 =  FavouriteRadioStation("1", "name1", "url1")
    private val favouriteRadioStation2 =  FavouriteRadioStation("2", "name2", "url2")

    @Before
    fun createDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RadioJuicerDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.favouriteRadioStationDao()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun upsert_newFavouriteRadioStation_newEntryAdded() = runBlocking{
        dao.upsert(favouriteRadioStation1)
        assertEquals(favouriteRadioStation1, dao.getAll().first()[0])
    }

    @Test
    fun upsert_updateFavouriteRadioStation_entryUpdated() = runBlocking{
        dao.upsert(favouriteRadioStation1)
        val updatedRadioStation = favouriteRadioStation1.copy(name = "updated name")
        dao.upsert(updatedRadioStation)
        assertEquals(updatedRadioStation, dao.getAll().first()[0])
    }

    @Test
    fun getAll_returnsAllSavedFavouriteRadioStations() = runBlocking {
        dao.upsert(favouriteRadioStation1)
        dao.upsert(favouriteRadioStation2)
        val favRadioStations = listOf(favouriteRadioStation1, favouriteRadioStation2)
        assertEquals(favRadioStations, dao.getAll().first())
    }

    @Test
    fun deleteAll_deleteAllFavouriteRadioStations() = runBlocking {
        dao.upsert(favouriteRadioStation1)
        dao.upsert(favouriteRadioStation2)
        dao.deleteAll()
        assertEquals(0, dao.getAll().first().size)
    }
}