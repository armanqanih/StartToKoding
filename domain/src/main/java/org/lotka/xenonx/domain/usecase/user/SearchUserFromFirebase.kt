package org.lotka.xenonx.domain.usecase.user

import org.lotka.xenonx.domain.repository.UserListRepository

class SearchUserFromFirebase(
    private val userListRepository: UserListRepository
) {
    suspend operator fun invoke(userEmail: String) = userListRepository.searchUserFromFirebase(userEmail)
}