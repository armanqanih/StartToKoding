package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository


class OpenBlockedFriendToFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(registerUUID: String) =
        userListScreenRepository.openBlockedFriendToFirebase(registerUUID)
}