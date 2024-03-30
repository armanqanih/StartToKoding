package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.user.UserListItem
import org.lotka.xenonx.domain.util.ResultState

interface UserListRepository {
    suspend fun getUserList(): Flow<ResultState<UserListItem>>
}