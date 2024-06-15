package org.lotka.xenonx.presentation.ui.screens.auth


sealed class AuthEvent{
    object SignIn : AuthEvent()
    object SignUp : AuthEvent()
    object SignOut : AuthEvent()
    data class EmailChanged(val email: String) : AuthEvent()
    data class PasswordChanged(val password: String) : AuthEvent()
    data class ShowSnackBar(val message: String) : AuthEvent()

}