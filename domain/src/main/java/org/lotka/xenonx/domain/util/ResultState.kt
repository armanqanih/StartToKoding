package org.lotka.xenonx.domain.util

import org.lotka.xenonx.domain.ErrorMessage2


sealed class ResultState<T> {
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val error: ErrorMessage2) : ResultState<T>()
    data class Loading<T>(val loading: Boolean) : ResultState<T>()
}

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(val data: T) : Response<T>()

    data class Error(val message: String) : Response<Nothing>()
}