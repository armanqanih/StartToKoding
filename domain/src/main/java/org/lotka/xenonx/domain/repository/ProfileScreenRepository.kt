package org.lotka.xenonx.domain.repository

import android.net.Uri
import com.example.chatwithme.domain.model.User
import com.example.chatwithme.domain.model.UserStatus
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.util.ResultState

interface ProfileScreenRepository {
    suspend fun signOut(): Flow<ResultState<Boolean>>
    suspend fun uploadPictureToFirebase(url: Uri): Flow<ResultState<String>>
    suspend fun createOrUpdateProfileToFirebase(user: User): Flow<ResultState<Boolean>>
    suspend fun loadProfileFromFirebase(): Flow<ResultState<User>>
    suspend fun setUserStatusToFirebase(userStatus: UserStatus): Flow<ResultState<Boolean>>
}