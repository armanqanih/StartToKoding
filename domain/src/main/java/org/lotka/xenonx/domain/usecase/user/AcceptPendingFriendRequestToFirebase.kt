package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository


class AcceptPendingFriendRequestToFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(registerUUID: String) =
        userListScreenRepository.acceptPendingFriendRequestToFirebase(registerUUID)
}