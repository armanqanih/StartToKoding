package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.model.model.user.UserItem
import org.lotka.xenonx.domain.repository.UserListRepository


class CreateChatRoomToFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(acceptorUUID: String) =
        userListScreenRepository.createChatRoomToFirebase(acceptorUUID)
}