package org.lotka.xenonx.domain.usecase.user


import org.lotka.xenonx.domain.repository.UserListRepository

class CheckChatRoomExistedFromFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(acceptorUUID: String) =
        userListScreenRepository.checkChatRoomExistedFromFirebase(acceptorUUID)
}