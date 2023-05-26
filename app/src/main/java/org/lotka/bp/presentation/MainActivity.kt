package org.lotka.bp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.bp.datastore.SettingsDataStore
import org.lotka.bp.presentation.navigation.Screen
import org.lotka.bp.presentation.ui.auth.LoginOptionsScreen
import org.lotka.bp.presentation.ui.auth.LoginOptionsViewModel
import org.lotka.bp.presentation.ui.recipe.RecipeDetailScreen
import org.lotka.bp.presentation.ui.recipe.RecipeViewModel
import org.lotka.bp.presentation.ui.recipe_list.RecipeListScreen
import org.lotka.bp.presentation.ui.recipe_list.RecipeListViewModel
import org.lotka.bp.presentation.util.ConnectivityManager
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        //    val navController = rememberNavController()

//            NavHost(navController = navController, startDestination = Screen.RecipeList.route) {

//
//                composable(route = Screen.RecipeList.route) { navBackStackEntry ->
//                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//                    val viewModel: RecipeListViewModel = viewModel("RecipeListViewModel", factory)
//                    RecipeListScreen(
//                        isDarkTheme = settingsDataStore.isDark.value,
//                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
//                        onToggleTheme = settingsDataStore::toggleTheme,
//                        onNavigateToRecipeDetailScreen = navController::navigate,
//                        onNavigateToAuth = {
//
//                            navController.navigate(Screen.LoginOptions.route)
//                        },
//                        viewModel = viewModel,
//                    )
//                }

//
//                composable(
//                    route = Screen.RecipeDetail.route + "/{recipeId}",
//                    arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
//                ) { navBackStackEntry ->
//                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//                    val viewModel: RecipeViewModel = viewModel("RecipeDetailViewModel", factory)
//                    RecipeDetailScreen(
//                        isDarkTheme = settingsDataStore.isDark.value,
//                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
//                        recipeId = navBackStackEntry.arguments?.getInt("recipeId"),
//                        viewModel = viewModel,
//                    )
//                }

//                composable(route = Screen.LoginOptions.route) { navBackStackEntry ->
//                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
//                    val viewModel: LoginOptionsViewModel =
//                        viewModel("LoginOptionsViewModel", factory)
//                    LoginOptionsScreen(
//                        isDarkTheme = settingsDataStore.isDark.value,
//                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
//                        viewModel = viewModel,
//                    )
//                }


            }

        }
    }
















