package org.lotka.xenonx.presentation.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems

import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.composables.compose.SearchBar
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.component.CoinsPaginationList
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1


@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(CoinModel) -> Unit
){


    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            onSearch = {
                event(SearchEvent.SearchNews)
            },
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))}    ,
            readOnly = false
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.coins?.let {
            val coins = it.collectAsLazyPagingItems()
            CoinsPaginationList(
                coins = coins,
                onClick = navigateToDetails
            )
        }

}}