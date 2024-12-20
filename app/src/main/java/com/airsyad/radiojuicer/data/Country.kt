package com.airsyad.radiojuicer.data

/**
 * Internal model class representing a country.
 *
 * This class bridges between layers and components that need to work with data related to a country.
 * External class or DTO class reprenting a country need to be converted to this class first before
 * used by other layers or components
 *
 * @countryCode according to ISO 3166-1
 */
data class Country(
    val name: String,
    val countryCode: String,
    val stationCount: Int
)