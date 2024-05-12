package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository


class CreateFriendListRegisterToFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(
        chatRoomUUID: String,
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ) = userListScreenRepository.createFriendListRegisterToFirebase(
        chatRoomUUID,
        acceptorEmail,
        acceptorUUID,
        acceptorOneSignalUserId
    )
}