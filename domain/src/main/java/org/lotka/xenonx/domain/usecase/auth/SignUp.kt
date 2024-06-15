package org.lotka.xenonx.domain.usecase.auth

import org.lotka.xenonx.domain.repository.AuthScreenRepository


class SignUp(
    private val authScreenRepository: AuthScreenRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authScreenRepository.signUp(email, password)
    }
