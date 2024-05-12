package org.lotka.xenonx.domain.usecase.user


import org.lotka.xenonx.domain.repository.UserListRepository

class LoadPendingFriendRequestListFromFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke() = userListScreenRepository.loadPendingFriendRequestListFromFirebase()
}