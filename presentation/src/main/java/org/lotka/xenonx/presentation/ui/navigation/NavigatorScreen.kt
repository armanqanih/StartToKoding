package org.lotka.xenonx.presentation.ui.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.lotka.xenonx.presentation.ui.screens.auth.sign_in.SignInScreen
import org.lotka.xenonx.presentation.ui.screens.auth.sign_up.SignUpScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")

@Composable
fun NavigatorScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackbarHostState: androidx.compose.material.SnackbarHostState,
    keyboardController: SoftwareKeyboardController
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

        },
        content = { paddingValues ->
            val bottomPadding = paddingValues.calculateBottomPadding()
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.SignIn.fullRoute,
                modifier = Modifier.padding(bottom = bottomPadding)
            ) {
                composable(
                    BottomNavItem.SignIn.fullRoute,
                    arguments = listOf(
                        navArgument("emailFromSignUp") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    ),
                ) {
                    val emailFromSignUp = remember {
                        it.arguments?.getString("emailFromSignUp")
                    }
                    SignInScreen(
                        emailFromSignUp = emailFromSignUp ?: "",
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                }
                composable(
                    BottomNavItem.SignUp.fullRoute,
                    arguments = listOf(
                        navArgument("emailFromSignIn") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    ),
                ) {
                    val emailFromSignIn = remember {
                        it.arguments?.getString("emailFromSignIn")
                    }
                    SignUpScreen(
                        emailFromSignIn = emailFromSignIn ?: "",
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        keyboardController = keyboardController
                    )
                }

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

//private fun navigateToDetails(navController: NavController, coin: CoinModel) {
//    navController.currentBackStackEntry?.savedStateHandle?.set("coin", coin)
//    navController.navigate(Route.DetailRoutScreen.route)
//}

