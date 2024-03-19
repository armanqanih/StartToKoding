package org.lotka.xenonx.domain.repository


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.AuthResult2
import org.lotka.xenonx.domain.util.ResultState

interface AuthRepository {

    fun loginUser(emile: String, password: String): Flow<ResultState<AuthResult2>>
    fun registerUser(
        userName: String,
        emile: String,
        password: String
    ): Flow<ResultState<AuthResult2>>


}