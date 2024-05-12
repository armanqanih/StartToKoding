package org.lotka.xenonx.data.repository.user

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.model.model.user.FriendListRegister
import org.lotka.xenonx.domain.model.model.user.FriendListRow
import org.lotka.xenonx.domain.model.model.user.UserItem
import org.lotka.xenonx.domain.util.ResultState

interface UserDataSource {
    suspend fun loadAcceptedFriendRequestListFromFirebase(): Flow<ResultState<List<FriendListRow>>>
    suspend fun loadPendingFriendRequestListFromFirebase(): Flow<ResultState<List<FriendListRegister>>>

    suspend fun searchUserFromFirebase(userEmail: String): Flow<ResultState<UserItem?>>

    suspend fun checkChatRoomExistedFromFirebase(acceptorUUID: String): Flow<ResultState<String>>
    suspend fun createChatRoomToFirebase(acceptorUUID: String): Flow<ResultState<String>>

    suspend fun checkFriendListRegisterIsExistedFromFirebase(
        acceptorEmail: String,
        acceptorUUID: String
    ): Flow<ResultState<FriendListRegister>>

    suspend fun createFriendListRegisterToFirebase(
        chatRoomUUID: String,
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ): Flow<ResultState<Boolean>>

    suspend fun acceptPendingFriendRequestToFirebase(registerUUID: String): Flow<ResultState<Boolean>>
    suspend fun rejectPendingFriendRequestToFirebase(registerUUID: String): Flow<ResultState<Boolean>>
    suspend fun openBlockedFriendToFirebase(registerUUID: String): Flow<ResultState<Boolean>>
}