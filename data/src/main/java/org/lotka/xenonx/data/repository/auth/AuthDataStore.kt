package org.lotka.xenonx.data.repository.auth


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.util.ResultState

import org.lotka.xenonx.domain.AuthResult2

interface AuthDataSource {
    fun loginUser(email: String, password: String): Flow<ResultState<AuthResult2>>
    fun registerUser(
        userName: String,
        emile: String,
        password: String
    ): Flow<ResultState<AuthResult2>>

}