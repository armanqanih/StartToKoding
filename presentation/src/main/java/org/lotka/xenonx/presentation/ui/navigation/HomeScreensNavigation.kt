package org.lotka.xenonx.presentation.ui.navigation

sealed class HomeScreensNavigation(val route: String) {


    object splash : HomeScreensNavigation(route = "splash")

    object plp : HomeScreensNavigation(route = "plp")
    object pdp : HomeScreensNavigation(route = "pdp")

    object HomeChatScreen : HomeScreensNavigation(route = "HomeChatScreen")
    object SingleChatScreen : HomeScreensNavigation(route = "SingleChatScreen")
    object Setting : HomeScreensNavigation(route = "setting")
    object ProfileScreen : HomeScreensNavigation(route = "profileScreen")


}