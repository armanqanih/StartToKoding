package org.lotka.xenonx.domain.usecase.profile

import com.example.chatwithme.domain.model.User

import org.lotka.xenonx.domain.repository.ProfileScreenRepository

class CreateOrUpdateProfileToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(user: User) =
        profileScreenRepository.createOrUpdateProfileToFirebase(user)
}