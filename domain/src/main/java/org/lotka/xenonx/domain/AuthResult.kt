package org.lotka.xenonx.domain

data class AuthResult2(
    val success: Boolean,
    val userId: String? = null,
    val errorMessage: String? = null
)