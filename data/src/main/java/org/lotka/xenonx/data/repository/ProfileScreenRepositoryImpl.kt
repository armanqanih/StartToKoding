package org.lotka.xenonx.data.repository

import android.net.Uri
import com.example.chatwithme.domain.model.User
import com.example.chatwithme.domain.model.UserStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.lotka.xenonx.domain.ErrorMessage2
import org.lotka.xenonx.domain.repository.ProfileScreenRepository
import org.lotka.xenonx.domain.util.ResultState
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileScreenRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val storage: FirebaseStorage
):ProfileScreenRepository {
    override suspend fun signOut(): Flow<ResultState<Boolean>> {
        return flow {
            try {
                auth.signOut().apply {
                    emit(ResultState.Success(true))
                }
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error(errorMessage))
            }
        }
    }

    override suspend fun uploadPictureToFirebase(url: Uri): Flow<ResultState<String>> {
        return flow {
            try {
                emit(ResultState.Loading(true))
                val uuidImage = UUID.randomUUID()
                val imageName = "images/$uuidImage.jpg"
                val storageRef = storage.reference.child(imageName)
                storageRef.putFile(url).apply {}.await()
                var downloadUrl = ""
                storageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl = it.toString()
                }.await()
                emit(ResultState.Success(downloadUrl))
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error(errorMessage))
            }
        }
    }

    override suspend fun createOrUpdateProfileToFirebase(user: User): Flow<ResultState<Boolean>> {
        return flow {
            try {
                emit(ResultState.Loading(true))
                val userUUID = auth.currentUser?.uid.toString()
                val userEmail = auth.currentUser?.email.toString()
                val oneSignalUserId = OneSignal.getDeviceState()?.userId.toString()

                val databaseReference =
                    database.getReference("Profiles").child(userUUID).child("profile")

                val childUpdates = mutableMapOf<String, Any>()

                childUpdates["/profileUUID/"] = userUUID
                childUpdates["/userEmail/"] = userEmail
                childUpdates["/oneSignalUserId/"] = oneSignalUserId
                if (user.userName != "") childUpdates["/userName/"] = user.userName
                if (user.userProfilePictureUrl != "") childUpdates["/userProfilePictureUrl/"] =
                    user.userProfilePictureUrl
                if (user.userSurName != "") childUpdates["/userSurName/"] = user.userSurName
                if (user.userBio != "") childUpdates["/userBio/"] = user.userBio
                if (user.userPhoneNumber != "") childUpdates["/userPhoneNumber/"] =
                    user.userPhoneNumber
                childUpdates["/status/"] = UserStatus.ONLINE.toString()

                databaseReference.updateChildren(childUpdates).await()
                emit(ResultState.Success(true))
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error(errorMessage))
            }
        }
    }

    override suspend fun loadProfileFromFirebase(): Flow<ResultState<User>> {
        return flow {
            try {
                emit(ResultState.Loading(true))
                val userUUID = auth.currentUser?.uid
                val databaseReference = database.getReference("Profiles")
                val postListener = databaseReference.addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userFromFirebaseDatabase =
                            snapshot.child(userUUID!!).child("profile").getValue(User::class.java)
                                ?: User()
                        (ResultState.Success(userFromFirebaseDatabase))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        val errorMessage =
                            ErrorMessage2(error.message ?: "An unknown error occurred")
                        CoroutineScope(Dispatchers.IO).launch {
                            emit(ResultState.Error(errorMessage))
                        }
                    }
                })
                databaseReference.addValueEventListener(postListener)
                databaseReference.removeEventListener(postListener)

            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error(errorMessage))
            }
        }
    }

    override suspend fun setUserStatusToFirebase(userStatus: UserStatus): Flow<ResultState<Boolean>> {
        return flow {
            try {
                emit(ResultState.Loading(true))
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    val userUUID = currentUser.uid

                    val databaseReference =
                        database.getReference("Profiles").child(userUUID).child("profile")
                            .child("status")
                    databaseReference.setValue(userStatus.toString()).await()
                    emit(ResultState.Success(true))
                } else {
                    emit(ResultState.Success(false))
                }
            } catch (e: Exception) {
                val errorMessage = ErrorMessage2(e.message ?: "An unknown error occurred")
                emit(ResultState.Error(errorMessage))
            }
        }
    }
}