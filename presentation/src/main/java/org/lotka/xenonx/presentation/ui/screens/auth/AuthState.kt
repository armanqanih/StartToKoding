package org.lotka.xenonx.presentation.ui.screens.auth

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class AuthState (
    val isUserSignUp : Boolean ?= false,
    val isUserSignIn: Boolean ?= false ,
    val isUserAuthenticatedState : Boolean ?= false ,
    val email : String = "",
    val password : String = "",
    val showSnakeBar : String = "" ,
)




