package org.lotka.xenonx.presentation.ui.screens.HomeScreen

sealed class HomeEvent {
    data class UpdateScrollValue(val newValue: Int): HomeEvent()
    data class UpdateMaxScrollingValue(val newValue: Int): HomeEvent()

}