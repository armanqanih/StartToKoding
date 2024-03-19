package org.lotka.xenonx.presentation.composables


data class AuthResult(
    val success: Boolean,
    val userId: String? = null,
    val errorMessage: String? = null
)