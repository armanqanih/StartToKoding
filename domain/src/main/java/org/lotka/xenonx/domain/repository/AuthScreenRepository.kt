package org.lotka.xenonx.domain.repository


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.util.Response
import org.lotka.xenonx.domain.util.Response1
import org.lotka.xenonx.domain.util.ResultState

interface AuthScreenRepository {
    fun isUserAuthenticatedInFirebase(): Flow<ResultState<Boolean>>
    suspend fun signIn(email: String, password: String): Flow<ResultState<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<ResultState<Boolean>>
}