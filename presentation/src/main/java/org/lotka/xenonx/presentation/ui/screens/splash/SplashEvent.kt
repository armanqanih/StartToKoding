package org.lotka.xenonx.presentation.ui.screens.splash

sealed class SplashEvent {
    class ShowMessage(val message: String?) : SplashEvent()
    object NavigateToListing : SplashEvent()

}