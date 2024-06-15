package org.lotka.xenonx.presentation.ui.screens.auth

data class AuthState(
    val isUserAuthenticatedState : Boolean = false,
    val isUserSignInState : Boolean ?= false,
    val isUserSignUpState : Boolean = false,
    val showSnakeBar : String = ""
)
