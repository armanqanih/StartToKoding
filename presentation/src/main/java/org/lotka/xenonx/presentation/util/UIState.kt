package org.lotka.xenonx.presentation.util

sealed class UIState {

    data object Idle : UIState()
    data object PaginationLoading : UIState()

    data object PaginationError : UIState()
    data object Loading : UIState()
    data class Error(val message: String? = null) : UIState()
    data object Success : UIState()
}