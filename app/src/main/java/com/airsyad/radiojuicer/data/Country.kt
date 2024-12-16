package com.airsyad.radiojuicer.data

/**
 * Model representing a country.
 * @countryCode according to ISO 3166-1
 */
data class Country(
    val name: String,
    val countryCode: String,
    val stationCount: Int
)