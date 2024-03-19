package org.lotka.xenonx.domain.util

import org.lotka.xenonx.domain.ErrorMessage2


sealed class ResultState<T> {
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val error: ErrorMessage2) : ResultState<T>()
    data class Loading<T>(val loading: Boolean) : ResultState<T>()
}

sealed class ResultState2<T> {
    data class Success<T>(val data: T?) : ResultState<T>()
    data class Error<T>(val error: Exception) : ResultState<T>()
    data class Loading<T>(val loading: Boolean) : ResultState<T>()
}