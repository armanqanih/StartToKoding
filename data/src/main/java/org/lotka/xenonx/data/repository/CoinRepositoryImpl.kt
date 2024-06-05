package org.lotka.xenonx.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val coinApi: CoinPaprikaApi
): CoinRepository {
    override fun getCoins(): Flow<PagingData<CoinModel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { CoinRemotePagingSources(coinApi = coinApi) }
        ).flow

    }


    override fun getCoinById(coinId: String): CoinModel {
        TODO("Not yet implemented")
    }
}