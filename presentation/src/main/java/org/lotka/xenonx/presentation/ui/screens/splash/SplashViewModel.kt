package org.lotka.xenonx.presentation.ui.screens.splash

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.lotka.xenonx.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.*


@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    private val _events = MutableSharedFlow<SplashEvent>()
    val events = _events.asSharedFlow()


    init {
        viewModelScope.launch {
            delay(3000) // Wait for 3 seconds
            navigateToListing() // This method will be called after 3 seconds
        }
    }

    private fun navigateToListing() {
        viewModelScope.launch {
            _events.emit(SplashEvent.NavigateToListing)
        }

    }


}