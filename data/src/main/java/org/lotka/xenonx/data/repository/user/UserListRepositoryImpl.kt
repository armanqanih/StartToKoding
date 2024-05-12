package org.lotka.xenonx.data.repository.user

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.user.FriendListRegister
import org.lotka.xenonx.domain.model.model.user.FriendListRow
import org.lotka.xenonx.domain.model.model.user.UserItem
import org.lotka.xenonx.domain.repository.UserListRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserListRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
):UserListRepository{
    override suspend fun loadAcceptedFriendRequestListFromFirebase(): Flow<ResultState<List<FriendListRow>>> {
       return dataSource.loadAcceptedFriendRequestListFromFirebase()
    }

    override suspend fun loadPendingFriendRequestListFromFirebase(): Flow<ResultState<List<FriendListRegister>>> {
         return dataSource.loadPendingFriendRequestListFromFirebase()
    }

    override suspend fun searchUserFromFirebase(userEmail: String): Flow<ResultState<UserItem?>> {
      return dataSource.searchUserFromFirebase(userEmail)
    }

    override suspend fun checkChatRoomExistedFromFirebase(acceptorUUID: String): Flow<ResultState<String>> {
      return dataSource.checkChatRoomExistedFromFirebase(acceptorUUID)
    }

    override suspend fun createChatRoomToFirebase(acceptorUUID: String): Flow<ResultState<String>> {
       return dataSource.createChatRoomToFirebase(acceptorUUID)
    }

    override suspend fun checkFriendListRegisterIsExistedFromFirebase(
        acceptorEmail: String,
        acceptorUUID: String
    ): Flow<ResultState<FriendListRegister>> {
        return dataSource.checkFriendListRegisterIsExistedFromFirebase(acceptorEmail, acceptorUUID)
    }

    override suspend fun createFriendListRegisterToFirebase(
        chatRoomUUID: String,
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ): Flow<ResultState<Boolean>> {
       return dataSource.createFriendListRegisterToFirebase(chatRoomUUID, acceptorEmail, acceptorUUID, acceptorOneSignalUserId)
    }

    override suspend fun acceptPendingFriendRequestToFirebase(registerUUID: String): Flow<ResultState<Boolean>> {
      return dataSource.acceptPendingFriendRequestToFirebase(registerUUID)
    }

    override suspend fun rejectPendingFriendRequestToFirebase(registerUUID: String): Flow<ResultState<Boolean>> {
       return dataSource.rejectPendingFriendRequestToFirebase(registerUUID)
    }

    override suspend fun openBlockedFriendToFirebase(registerUUID: String): Flow<ResultState<Boolean>> {
       return dataSource.openBlockedFriendToFirebase(registerUUID)
    }

}