package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository


class RejectPendingFriendRequestToFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(registerUUID: String) =
        userListScreenRepository.rejectPendingFriendRequestToFirebase(registerUUID)
}