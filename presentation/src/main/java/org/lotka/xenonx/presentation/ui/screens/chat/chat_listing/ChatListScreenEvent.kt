package org.lotka.xenonx.presentation.ui.screens.chat.chat_listing

sealed class ChatListScreenEvent {

//    object NavigateToSignUp : PlpScreenEvent()
//    class ShowSnackBar(val message: String?) : PlpScreenEvent()

    object NewSearchEvent : ChatListScreenEvent()

    object NextPageEvent : ChatListScreenEvent()

    object SearchLocationPhrase : ChatListScreenEvent()

    object RestoreStateEvent : ChatListScreenEvent()
}