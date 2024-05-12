package org.lotka.xenonx.domain.usecase.user


import org.lotka.xenonx.domain.repository.UserListRepository

class CheckFriendListRegisterIsExistedFromFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke(
        acceptorEmail: String,
        acceptorUUID: String
    ) =
        userListScreenRepository.checkFriendListRegisterIsExistedFromFirebase(
            acceptorEmail,
            acceptorUUID
        )
}