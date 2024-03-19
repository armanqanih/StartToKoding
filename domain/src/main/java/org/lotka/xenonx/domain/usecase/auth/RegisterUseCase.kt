package org.lotka.xenonx.domain.usecase.auth


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.repository.AuthRepository
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.domain.AuthResult2
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(
        username: String,
        email: String,
        password: String
    ): Flow<ResultState<AuthResult2>> {
        return (repository.registerUser(username, email, password))

    }


}
