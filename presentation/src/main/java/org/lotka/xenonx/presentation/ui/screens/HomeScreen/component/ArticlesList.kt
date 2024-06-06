package org.lotka.xenonx.presentation.ui.screens.HomeScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

import org.lotka.xenonx.domain.model.CoinModel

import org.lotka.xenonx.presentation.composables.compose.ArticleCardShimmerEffect
import org.lotka.xenonx.presentation.composables.compose.EmptyScreen

import org.lotka.xenonx.presentation.util.Dimens.ExtraSmallPadding2
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1


@Composable
fun CoinsList(
    modifier: Modifier = Modifier,
    coins: List<CoinModel>,
    onClick: (CoinModel) -> Unit
) {
    if (coins.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(coins.size) {
            coins[it]?.let { coin ->
                CoinCard(coin = coin, onClick = { onClick(coin) })
            }
        }
    }

}

@Composable
fun CoinsPaginationList(
    modifier: Modifier = Modifier,
    coins: LazyPagingItems<CoinModel>,
    onClick: (CoinModel) -> Unit
) {

    val handlePagingResult = handlePagingResult(coins)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = coins.itemCount,
            ) {
                coins[it]?.let { coin ->
                    CoinCard(coin = coin, onClick = { onClick(coin) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(coins: LazyPagingItems<CoinModel>): Boolean {
    val loadState = coins.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(20) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}