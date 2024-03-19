package org.lotka.xenonx.data.repository.auth


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.domain.AuthResult2
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    val storage: FirebaseStorage,
    val context: android.content.Context
) : AuthDataSource {

    override fun loginUser(email: String, password: String): Flow<ResultState<AuthResult2>> {
        return flow {
            emit(ResultState.Loading(true))
            // val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(ResultState.Success(AuthResult2(success = true, userId = 12.toString())))
            emit(ResultState.Loading(false))
        }.catch { e ->
//            emit(ResultState.Error(AuthResult(success = false, errorMessage = e.message)))
        }
    }

    override fun registerUser(
        userName: String,
        email: String,
        password: String
    ): Flow<ResultState<AuthResult2>> {
        return flow {
            emit(ResultState.Loading(true))
//            val authResult =
//                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            emit(ResultState.Success(AuthResult2(success = true, userId = 12.toString())))
            emit(ResultState.Loading(false))
        }.catch { e ->
//            emit(ResultState.Error(AuthResult(success = false, errorMessage = e.message)))
        }
    }


}




