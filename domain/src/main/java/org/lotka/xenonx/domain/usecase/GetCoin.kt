package org.lotka.xenonx.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository

import javax.inject.Inject

class GetCoin @Inject constructor(
    private val coinRepository: CoinRepository
) {
     operator fun invoke(): Flow<PagingData<CoinModel>> {
        return coinRepository.getCoins()
    }

}