package org.lotka.xenonx.data.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.model.CoinDto
import org.lotka.xenonx.data.model.toCoin
import org.lotka.xenonx.domain.model.CoinModel
import java.lang.Exception

class SearchForCoinRemotePagingSources (
    private val coinApi: CoinPaprikaApi,
    private val searchQuery: String,
): PagingSource<Int, CoinModel>(){
    override fun getRefreshKey(state: PagingState<Int, CoinModel>): Int? {
        return state.anchorPosition?.let { anchorPage ->
            val page = state.closestPageToPosition(anchorPage)
            page?.nextKey?.minus(1) ?: page?.prevKey?.plus(1)
        }
    }

    private var totalCoinsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinModel> {
        val page = params.key ?: 1
        return try {
            val coinResponse = coinApi.searchCoins(searchQuery = searchQuery, page = page )
            val coins = coinResponse.map { it.toCoin() }.distinctBy { it.name } // Convert to CoinModel and remove duplicates
            totalCoinsCount += coins.size

            LoadResult.Page(
                data = coins,
                nextKey = if (coins.isEmpty()) null else page + 1, // Determine next key
                prevKey = if (page == 1) null else page - 1 // Determine previous key
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }}