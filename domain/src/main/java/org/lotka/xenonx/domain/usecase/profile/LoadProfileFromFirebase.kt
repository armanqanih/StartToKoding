package org.lotka.xenonx.domain.usecase.profile

import org.lotka.xenonx.domain.repository.ProfileScreenRepository


class LoadProfileFromFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke() = profileScreenRepository.loadProfileFromFirebase()
}