package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository


class LoadAcceptedFriendRequestListFromFirebase(
    private val userListScreenRepository: UserListRepository
) {
    suspend operator fun invoke() = userListScreenRepository.loadAcceptedFriendRequestListFromFirebase()
}