package org.lotka.xenonx.data.repository

import org.lotka.xenonx.domain.ErrorMessage2
import org.lotka.xenonx.domain.repository.AuthScreenRepository

import org.lotka.xenonx.domain.util.ResultState


import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthScreenRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthScreenRepository {
    override fun isUserAuthenticatedInFirebase(): Flow<ResultState<Boolean>> {
        return flow {
            try {
                val isUserAuthenticated = auth.currentUser != null
                emit(ResultState.Success(isUserAuthenticated))
            } catch (e: Exception) {
                // Assuming ErrorMessage2 takes a string message
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error<Boolean>(errorMessage))
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Flow<ResultState<Boolean>> {
        return flow {
            emit(ResultState.Loading(true))
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                emit(ResultState.Success(true))
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "Sign-in failed")
                emit(ResultState.Error<Boolean>(errorMessage))
            } finally {
                emit(ResultState.Loading(false))
            }
        }

    }

    override suspend fun signUp(email: String, password: String): Flow<ResultState<Boolean>> {
        return flow {
            emit(ResultState.Loading(true))
            try {
                auth.createUserWithEmailAndPassword(email, password).await()
                emit(ResultState.Success(true))
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "Sign-up failed")
                emit(ResultState.Error<Boolean>(errorMessage))
            } finally {
                emit(ResultState.Loading(false))
            }
        }

    }

}
