package org.lotka.xenonx.data.repository.auth

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.repository.AuthRepository
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.domain.AuthResult2
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<ResultState<AuthResult2>> {
        return (dataSource.loginUser(email, password))
    }

    override fun registerUser(
        userName: String,
        emile: String,
        password: String
    ): Flow<ResultState<AuthResult2>> {
        return (dataSource.registerUser(userName, emile, password))
    }


}





