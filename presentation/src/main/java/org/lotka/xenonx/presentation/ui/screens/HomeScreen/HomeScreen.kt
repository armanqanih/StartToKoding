package org.lotka.xenonx.presentation.ui.screens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems


import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.compose.SearchBar
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.component.CoinsPaginationList
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1


@Composable
fun HomeScreen(
    viewModel: HomeViewModel= hiltViewModel(),
    coins: LazyPagingItems<CoinModel>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (CoinModel) -> Unit,

    ){

    val titles by remember {
        derivedStateOf {
            if (coins.itemCount > 10) {
                coins.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.name }
            } else {
                ""
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(MediumPadding1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = navigateToSearch
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        val scrollState = rememberScrollState(initial = viewModel.scrollValue.intValue)
        val maxScrollingValue = rememberScrollState(initial = viewModel.maxScrollingValue.intValue)

        androidx.compose.material3.Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .horizontalScroll(scrollState, enabled = false),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        // Update the maxScrollingValue
        LaunchedEffect(key1 = scrollState.maxValue) {
           viewModel.onEvent(HomeEvent.UpdateMaxScrollingValue(scrollState.maxValue))
        }
        // Save the state of the scrolling position
        LaunchedEffect(key1 = scrollState.value) {
            viewModel.onEvent(HomeEvent.UpdateScrollValue(scrollState.value))
        }
        // Animate the scrolling

        Spacer(modifier = Modifier.height(MediumPadding1))

        CoinsPaginationList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            coins = coins,
            onClick = navigateToDetails
        )
    }







}