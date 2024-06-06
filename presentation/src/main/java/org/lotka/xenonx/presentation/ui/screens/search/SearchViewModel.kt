package org.lotka.xenonx.presentation.ui.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

import dagger.hilt.android.lifecycle.HiltViewModel
import org.lotka.xenonx.domain.usecase.SearchForCoins

import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
   private val searchForCoins: SearchForCoins
) : ViewModel() {

   private var _state = mutableStateOf(SearchState())
   val state: State<SearchState> = _state


   fun onEvent(event: SearchEvent) {
      when (event) {
         is SearchEvent.UpdateSearchQuery -> {
            _state.value = _state.value.copy(searchQuery = event.searchQuery)
         }

         is SearchEvent.SearchNews -> {
            searchCoins()
         }
      }
   }

   private fun searchCoins() {
      val coins = searchForCoins.invoke(
         searchCoins = _state.value.searchQuery
      ).cachedIn(viewModelScope)
      _state.value = _state.value.copy(coins = coins)
   }


}