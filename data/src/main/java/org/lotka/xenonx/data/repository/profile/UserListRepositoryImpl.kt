package org.lotka.xenonx.data.repository.profile

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.repository.home.HomeDataSource
import org.lotka.xenonx.domain.model.model.user.UserListItem
import org.lotka.xenonx.domain.repository.UserListRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject

class UserListRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
):UserListRepository{
    override suspend fun getUserList(): Flow<ResultState<UserListItem>> {
       return dataSource.getUserList()
    }
}