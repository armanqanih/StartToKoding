package org.lotka.xenonx.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository

import javax.inject.Inject

class SearchForCoins @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(searchCoins:String): Flow<PagingData<CoinModel>> {
        return coinRepository.searchNews(searchCoins)
    }

}