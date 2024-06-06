package org.lotka.xenonx.presentation.ui.screens.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

import org.lotka.xenonx.domain.model.CoinModel

data class SearchState(
    val searchQuery : String = "",
    val coins : Flow<PagingData<CoinModel>>? = null
)

