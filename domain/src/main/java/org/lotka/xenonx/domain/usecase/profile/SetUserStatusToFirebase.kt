package org.lotka.xenonx.domain.usecase.profile

import com.example.chatwithme.domain.model.UserStatus
import org.lotka.xenonx.domain.repository.ProfileScreenRepository


class SetUserStatusToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(userStatus: UserStatus) =
        profileScreenRepository.setUserStatusToFirebase(userStatus)
}