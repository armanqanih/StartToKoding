package org.lotka.xenonx.presentation.ui.screens.chat.user_list
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.enums.FriendStatus
import org.lotka.xenonx.domain.model.model.user.FriendListRegister
import org.lotka.xenonx.domain.model.model.user.FriendListRow
import org.lotka.xenonx.domain.usecase.user.UserListScreenUseCases
import org.lotka.xenonx.domain.util.Constants
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListScreenUseCases: UserListScreenUseCases
) : ViewModel() {
    var pendingFriendRequestList = mutableStateOf<List<FriendListRegister>>(listOf())
        private set

    var acceptedFriendRequestList = mutableStateOf<List<FriendListRow>>(listOf())
        private set

    var isRefreshing = mutableStateOf(false)
        private set

    var toastMessage = mutableStateOf("")
        private set


    fun refreshingFriendList() {
        viewModelScope.launch {
            isRefreshing.value = true
            loadPendingFriendRequestListFromFirebase()
            loadAcceptFriendRequestListFromFirebase()
            delay(1000)
            isRefreshing.value = false
        }
    }

    fun createFriendshipRegisterToFirebase(acceptorEmail: String) {
        //Search User -> Check Chat Room -> Create Chat Room -> Check FriendListRegister -> Create FriendListRegister
        viewModelScope.launch {
            userListScreenUseCases.searchUserFromFirebase.invoke(acceptorEmail)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            toastMessage.value = ""
                        }
                        is ResultState.Success -> {
                            if (result.data != null) {
                                checkChatRoomExistFromFirebaseAndCreateIfNot(
                                    acceptorEmail,
                                    result.data!!.profileUUID,
                                    result.data!!.oneSignalUserId
                                )
                            }
                        }
                        is ResultState.Error -> {}

                    }

                }
        }
    }

    fun acceptPendingFriendRequestToFirebase(registerUUID: String) {
        viewModelScope.launch {
            userListScreenUseCases.acceptPendingFriendRequestToFirebase.invoke(registerUUID)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            toastMessage.value = ""
                        }
                        is ResultState.Success -> {
                            toastMessage.value = "Friend Request Accepted"
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    fun cancelPendingFriendRequestToFirebase(registerUUID: String) {
        viewModelScope.launch {
            userListScreenUseCases.rejectPendingFriendRequestToFirebase.invoke(registerUUID)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            toastMessage.value = ""
                        }
                        is ResultState.Success -> {
                            toastMessage.value = "Friend Request Canceled"
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    private fun loadAcceptFriendRequestListFromFirebase() {
        viewModelScope.launch {
            userListScreenUseCases.loadAcceptedFriendRequestListFromFirebase.invoke()
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {}
                        is ResultState.Success -> {
                            if (result.data?.isNotEmpty() == true) {
                                acceptedFriendRequestList.value = result.data!!
                            }
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    private fun loadPendingFriendRequestListFromFirebase() {
        viewModelScope.launch {
            userListScreenUseCases.loadPendingFriendRequestListFromFirebase.invoke()
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {}
                        is ResultState.Success -> {
                            pendingFriendRequestList.value = result.data!!
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    private fun checkChatRoomExistFromFirebaseAndCreateIfNot(
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorSignalUserId: String
    ) {
        viewModelScope.launch {
            userListScreenUseCases.checkChatRoomExistedFromFirebase.invoke(acceptorUUID)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {}
                        is ResultState.Success -> {
                            if (result.data == Constants.NO_CHATROOM_IN_FIREBASE_DATABASE) {
                                createChatRoomToFirebase(
                                    acceptorEmail,
                                    acceptorUUID,
                                    acceptorSignalUserId
                                )
                            } else {
                                result.data?.let {
                                    checkFriendListRegisterIsExistFromFirebase(
                                        it,
                                        acceptorEmail,
                                        acceptorUUID,
                                        acceptorSignalUserId
                                    )
                                }
                            }
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    private fun createChatRoomToFirebase(
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ) {
        viewModelScope.launch {
            userListScreenUseCases.createChatRoomToFirebase.invoke(acceptorUUID)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {}
                        is ResultState.Success -> {
                            //Chat Room Created.
                            result.data?.let {
                                checkFriendListRegisterIsExistFromFirebase(
                                    it,
                                    acceptorEmail,
                                    acceptorUUID,
                                    acceptorOneSignalUserId
                                )
                            }
                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }

    private fun checkFriendListRegisterIsExistFromFirebase(
        chatRoomUUID: String,
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ) {
        viewModelScope.launch {
            userListScreenUseCases.checkFriendListRegisterIsExistedFromFirebase.invoke(
                acceptorEmail,
                acceptorUUID
            ).collect { response ->
                when (response) {
                    is ResultState.Loading -> {
                        toastMessage.value = ""
                    }
                    is ResultState.Success -> {
                        if (response.data?.equals(FriendListRegister()) == true) {
                            toastMessage.value = "Friend Request Sent."
                            createFriendListRegisterToFirebase(
                                chatRoomUUID,
                                acceptorEmail,
                                acceptorUUID,
                                acceptorOneSignalUserId
                            )
                        } else if (response.data?.status.equals(FriendStatus.PENDING.toString())) {
                            toastMessage.value = "Already Have Friend Request"
                        } else if (response.data?.status.equals(FriendStatus.ACCEPTED.toString())) {
                            toastMessage.value = "You Are Already Friend."
                        } else if (response.data?.status.equals(FriendStatus.BLOCKED.toString())) {
                            response.data?.registerUUID?.let { openBlockedFriendToFirebase(it) }
                        }
                    }
                    is ResultState.Error -> {}
                }
            }
        }
    }

    private fun createFriendListRegisterToFirebase(
        chatRoomUUID: String,
        acceptorEmail: String,
        acceptorUUID: String,
        acceptorOneSignalUserId: String
    ) {
        viewModelScope.launch {
            userListScreenUseCases.createFriendListRegisterToFirebase.invoke(
                chatRoomUUID,
                acceptorEmail,
                acceptorUUID,
                acceptorOneSignalUserId
            ).collect { result ->
                when (result) {
                    is ResultState.Loading -> {}
                    is ResultState.Success -> {
                    }
                    is ResultState.Error -> {}
                }

            }
        }
    }

    private fun openBlockedFriendToFirebase(registerUUID: String) {
        viewModelScope.launch {
            userListScreenUseCases.openBlockedFriendToFirebase.invoke(registerUUID)
                .collect { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            toastMessage.value = ""
                        }
                        is ResultState.Success -> {
                            if (result.data == true) {
                                toastMessage.value = "User Block Opened And Accept As Friend"
                            } else {
                                toastMessage.value = "You Are Blocked by User"
                            }

                        }
                        is ResultState.Error -> {}
                    }
                }
        }
    }
}