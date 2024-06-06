package org.lotka.xenonx.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.CoinModel

interface CoinRepository {

    fun  getCoins(): Flow<PagingData<CoinModel>>

    fun  getCoinById(coinId: String): CoinModel

    fun searchNews(searchQuery: String): Flow<PagingData<CoinModel>>


}