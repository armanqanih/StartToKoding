package org.lotka.xenonx.data.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.model.CoinDto
import org.lotka.xenonx.data.model.toCoin
import org.lotka.xenonx.domain.model.CoinModel

class CoinRemotePagingSources (
    private val coinApi: CoinPaprikaApi
): PagingSource<Int, CoinModel>(){
    override fun getRefreshKey(state: PagingState<Int, CoinModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {
        val page = params.key ?: 1
        return try {
            val coinResponse = coinApi.getCoins()
            val coins = coinResponse.map { it.toCoin() }  // Convert to CoinModel
            totalNewsCount += coins.size

            LoadResult.Page(
                data = coins,
                nextKey = if (coins.isEmpty()) null else page + 1,  // Determine next key
                prevKey = if (page == 1) null else page - 1  // Determine previous key
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }



}