package org.lotka.xenonx.presentation.ui.navigation

sealed class Route(val route: String) {

    object OnBoardingScreen : Route(route = "onBoardingScreen")

    object DetailRoutScreen : Route(route = "Detail_Screen")
    object BookMarkRoutScreen : Route(route = "BookMark_Screen")
    object HomeRoutScreen : Route(route = "Home_Screen")
    object SearchRouteScreen : Route(route = "Search_Screen")
    object NavigatorRoutScreen : Route(route = "HomeChatScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigator")

}