package org.lotka.xenonx.domain.usecase.auth


import org.lotka.xenonx.domain.repository.AuthScreenRepository

class IsUserAuthenticatedInFirebase(
    private val authScreenRepository: AuthScreenRepository
) {
  operator fun invoke() =
    authScreenRepository.isUserAuthenticatedInFirebase()
  }
