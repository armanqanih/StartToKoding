package org.lotka.xenonx.domain.usecase.auth


import org.lotka.xenonx.domain.repository.AuthScreenRepository

class SignIn(
    private val authScreenRepository: AuthScreenRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authScreenRepository.signIn(email, password)

    }
