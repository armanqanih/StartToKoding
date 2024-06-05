package org.lotka.xenonx.presentation.ui.screens.HomeScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.lotka.xenonx.domain.usecase.GetCoin
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinUseCase: GetCoin
):ViewModel(){

    val coins = getCoinUseCase()



   var scrollValue = mutableIntStateOf(HomeState().scrollValue)

   var maxScrollingValue = mutableIntStateOf(HomeState().maxScrollingValue)


    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    fun updateNewsTicker(news: String) {
        _state.update { it.copy(newsTicker = news) }
    }
    fun setLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
    private fun updateScrollValue(newScrollValue: Int) {
        _state.update { it.copy(scrollValue = newScrollValue) }
    }
    private fun setMaxScrollingValue(maxValue: Int) {
        _state.update { it.copy(maxScrollingValue = maxValue) }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is HomeEvent.UpdateMaxScrollingValue -> setMaxScrollingValue(event.newValue)
        }
    }



    }