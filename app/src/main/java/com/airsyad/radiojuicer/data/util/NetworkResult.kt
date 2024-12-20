package com.airsyad.radiojuicer.data.util

/**
 * Used to encapsulate result returned by any network request
 */
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data object Loading: NetworkResult<Nothing>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}