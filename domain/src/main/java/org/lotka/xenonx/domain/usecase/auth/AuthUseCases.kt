package org.lotka.xenonx.domain.usecase.auth

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticatedInFirebase,
    val signIn: SignIn,
    val signUp: SignUp,
)