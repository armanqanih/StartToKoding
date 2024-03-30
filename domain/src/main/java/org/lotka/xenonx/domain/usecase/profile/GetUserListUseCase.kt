package org.lotka.xenonx.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.user.UserListItem
import org.lotka.xenonx.domain.repository.UserListRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetUserListUseCase @Inject
constructor(private val repository: UserListRepository) {
    suspend operator fun invoke(): Flow<ResultState<UserListItem>> {
        return repository.getUserList()

    }


}
