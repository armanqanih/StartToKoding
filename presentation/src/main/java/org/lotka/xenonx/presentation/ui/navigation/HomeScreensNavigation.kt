package org.lotka.xenonx.presentation.ui.navigation

sealed class HomeScreensNavigation(val route: String) {




    object HomeChatScreen : HomeScreensNavigation(route = "HomeChatScreen")
    object single_chat_screen : HomeScreensNavigation(route = "SingleChatScreen")

    object NavigatorRoutScreen : Route(route = "HomeChatScreen")



}