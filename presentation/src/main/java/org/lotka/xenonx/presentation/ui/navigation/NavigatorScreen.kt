package org.lotka.xenonx.presentation.ui.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems

import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.HomeScreen
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.HomeViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun NavigatorScreen(

) {
    val navController = rememberNavController()

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeRoutScreen.route -> 0
        Route.SearchRouteScreen.route -> 1
        Route.BookMarkRoutScreen.route -> 2
        else -> 0
    }

    // Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeRoutScreen.route ||
                backStackState?.destination?.route == Route.SearchRouteScreen.route ||
                backStackState?.destination?.route == Route.BookMarkRoutScreen.route
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeRoutScreen.route)
                            1 -> navigateToTab(navController, Route.SearchRouteScreen.route)
                            2 -> navigateToTab(navController, Route.BookMarkRoutScreen.route)
                        }
                    }
                )
            }
        },
        content = { paddingValues ->
            val bottomPadding = paddingValues.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = Route.HomeRoutScreen.route,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                composable(route = Route.HomeRoutScreen.route) { backStackEntry ->
                    val viewModel: HomeViewModel = hiltViewModel()
                    val coins = viewModel.coins.collectAsLazyPagingItems()
                    HomeScreen(
                        coins = coins,
                        navigateToSearch = { navigateToTab(navController, Route.SearchRouteScreen.route) },
                        navigateToDetails = { article -> navigateToDetails(navController, article) }
                    )
                }
//                composable(route = Route.SearchRouteScreen.route) {
//                    val viewModel: SearchViewModel = hiltViewModel()
//                    val state = viewModel.state.value
//                    OnBackClickStateSaver(navController = navController)
//                    SearchScreen(
//                        state = state,
//                        event = viewModel::onEvent,
//                        navigateToDetails = { article -> navigateToDetails(navController, article) }
//                    )
//                }
//                composable(route = Route.DetailRoutScreen.route) {
//                    val viewModel: DetailViewModel = hiltViewModel()
//                    navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article ->
//                        DetailsScreen(
//                            article = article,
//                            event = viewModel::onEvent,
//                            navigateUp = { navController.navigateUp() },
//                            sideEffect = viewModel.sideEffect
//                        )
//                    }
//                }
//                composable(route = Route.BookMarkRoutScreen.route) {
//                    val viewModel: BookMarkViewModel = hiltViewModel()
//                    val state = viewModel.state.value
//                    OnBackClickStateSaver(navController = navController)
//                    BookmarkScreen(
//                        state = state,
//                        navigateToDetails = { article -> navigateToDetails(navController, article) }
//                    )
//                }
            }
        }
    )
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(navController, Route.HomeRoutScreen.route)
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, coin: CoinModel) {
    navController.currentBackStackEntry?.savedStateHandle?.set("coin", coin)
    navController.navigate(Route.DetailRoutScreen.route)
}

