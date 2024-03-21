package org.lotka.xenonx.data.repository.home


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.lotka.xenonx.data.model.remote.chat_list.ChatListResponseItemRemote
import org.lotka.xenonx.data.model.remote.chat_list.ChatListResponseRemote
import org.lotka.xenonx.data.model.remote.chat_list.toDomain
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.domain.util.Sektorum
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSource @Inject constructor(
    @Sektorum private val apiService: org.lotka.xenonx.data.api.HomeService,
    private val cdnService: org.lotka.xenonx.data.api.CdnService,
    private val database: FirebaseDatabase,
    private val auth  : FirebaseAuth,
    private val apiExceptionHandler: org.lotka.xenonx.data.exceptions.NetworkExceptionHandler,
) : HomeDataSource {

    init {
        auth.signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("pshiiisagi", " isSuccessful ${auth.currentUser?.uid}  s" )
            }
        }

    }
    override suspend fun searchLocation(text: String): ResultState<LocationSearchModel> {
        return try {
            val result = apiService.fuzzySearchCountry(phrase = text)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


    override suspend fun pdpDetail(id: Int): ResultState<PdpModel> {
        return try {
            val result = apiService.getSingleListing(identifier = id)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun pushNewChatListModel(id: Int): ResultState<Boolean> {
        return try {
            //get time now in milisecond for greenwitch
            val now = System.currentTimeMillis()
            val username = auth.currentUser?.uid
            val fakeChatItem = ChatListResponseItemRemote(
                chatId = "fakeChatId",
                userId = "fakeUserId",
                userNickName = "fakeUserNickName",
                smallProfileImage = "fakeImageUrl",
                lastMessageText = "fakeLastMessageText",
                lastMessageTime = now,
                numUnreadMessage = 0L,
                lastOnlineTime = now,
                lastTypingTime = now,
                lastMessageType = "fakeLastMessageType",
                lastChatSeenTime = now,
                lastMessageStatus = "fakeLastMessageStatus",
                verificationStatus = "GREEN",
                isSilent = false,
                isLockAccount = false,
                isChatPinned = false,
                isPremiumUser = false
            )

            val chatListRef = database.reference.child("chat_list/$username/")

            // Push the new chat item to Firebase, generating a unique ID for it
            chatListRef.push().setValue(fakeChatItem)
                .addOnSuccessListener {
                    Log.d("AddChatItem", "Chat item successfully added.")
                }
                .addOnFailureListener {
                    Log.d("AddChatItem", "Failed to add chat item: ${it.message}")
                }
            ResultState.Success(null)
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }





    override fun observeUserChatList(page: Int): Flow<ResultState<ChatListResponseModel>> = callbackFlow {
        Log.d("pshiiisagi", "getInstance")
        val chatListReference = database.reference.child("chat_list/${auth.currentUser?.uid}/")
        val query: Query = chatListReference.orderByChild("lastMessageTime").limitToLast(100)

        // Initialize the chat list outside of the event listener to maintain state across events
        val chatList = mutableListOf<ChatListResponseItemRemote>()
        val eventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                try {
                    val chatItem = snapshot.getValue(ChatListResponseItemRemote::class.java)
                    chatItem?.let {
                        chatList.add(chatItem)
                    }
                    emitCurrentList()
                }catch (e: Exception){
                    Log.d("pshiiisagi", "onChildAdded excepion:  ${e.message} and ${e.localizedMessage} and ${e.stackTrace} and ${e.printStackTrace()}")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                try {
                    snapshot.getValue(ChatListResponseItemRemote::class.java)?.let { updatedItem ->
                            val index = chatList.indexOfFirst { it.chatId == updatedItem.chatId }
                            if (index != -1) {
                                chatList[index] = updatedItem
                                emitCurrentList()
                            }
                        }

                }catch (e: Exception){
                    Log.d("pshiiisagi", "excepionwq:  ${e.message} and ${e.localizedMessage} and ${e.stackTrace} and ${e.printStackTrace()}")
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                try {
                    snapshot.getValue(ChatListResponseItemRemote::class.java)?.let { removedItem ->
                            chatList.removeAll { it.chatId == removedItem.chatId }
                            emitCurrentList()
                        }

                }catch (e: Exception){
                    Log.d("pshiiisagi", "excepionwq:  ${e.message} and ${e.localizedMessage} and ${e.stackTrace} and ${e.printStackTrace()}")
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle if items are moved. This may not be necessary for your use case.
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle any database cancellations
                Log.d("pshiiisagi", "onCancelled: ${databaseError.toException()} and ${databaseError.message}")
                trySend(ResultState.Error(apiExceptionHandler.traceErrorException(databaseError.toException()))).isFailure
            }

            private fun emitCurrentList() {
                val sortedCharList = chatList.sortedByDescending { it.lastMessageTime }
                val responseModel = ChatListResponseRemote(sortedCharList, chatList.size)
                trySend(ResultState.Success(responseModel.toDomain())).isSuccess
            }
        }

        query.addChildEventListener(eventListener)
        awaitClose { query.removeEventListener(eventListener) }
    }


    override suspend fun getContactInformation(identifier: Int): ResultState<ContactInformation> {
        return try {
            val result = apiService.getContactInfo(identifier = identifier)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun getAppStatus(): ResultState<AppStatusResponse> {
        return try {
            val result = cdnService.checkAppStatus()
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


}

