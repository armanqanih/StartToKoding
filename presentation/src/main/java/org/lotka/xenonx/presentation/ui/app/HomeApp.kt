package org.lotka.xenonx.presentation.ui.app




import android.annotation.SuppressLint
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import org.lotka.xenonx.presentation.ui.navigation.HomeScreensNavigation
import org.lotka.xenonx.presentation.ui.navigation.NavigatorScreen
import org.lotka.xenonx.presentation.ui.screens.plp.PlpScreen
import org.lotka.xenonx.presentation.ui.screens.plp.PlpViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeApp(
    activity: HomeActivity,
    navController: NavHostController,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    keyboardController: SoftwareKeyboardController,
    plpViewModel: PlpViewModel
    ) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scaffoldState = rememberScaffoldState()




    Scaffold(

        content = { _ ->
            NavHost(navController = navController,
                startDestination = HomeScreensNavigation.NavigatorRoutScreen.route,
                enterTransition = {
                    // you can change whatever you want transition
                    EnterTransition.None
                },
                exitTransition = {
                    // you can change whatever you want transition
                    ExitTransition.None
                }) {
                composable(
                    route = HomeScreensNavigation.NavigatorRoutScreen.route,
                ) {

                    NavigatorScreen()

//                  PlpScreen(
//                      navController = navController,
//                      onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen,
//                      isDarkTheme = isDarkTheme,
//                      onToggleTheme = onToggleTheme,
//                      viewModel = plpViewModel
//                  )

                }
                composable(
                    route = HomeScreensNavigation.single_chat_screen.route,
                ) {


                }

            }

        },
    )

}



