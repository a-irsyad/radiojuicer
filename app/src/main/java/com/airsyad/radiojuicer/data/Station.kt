package com.airsyad.radiojuicer.data

/**
 * Internal model class representing a Station.
 *
 * This class bridges between layers and components that need to work with data related to a Station.
 * External class or DTO class reprenting a Station need to be converted to this class first before
 * used by other layers or components
 *
 */
data class Station(
    val stationUiid: String,
    val name: String,
    val logoUrl: String
)