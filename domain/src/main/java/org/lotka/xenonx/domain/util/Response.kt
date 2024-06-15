package org.lotka.xenonx.domain.util

sealed class Response1<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(val data: T) : Response<T>()

    data class Error(val message: String) : Response<Nothing>()
}