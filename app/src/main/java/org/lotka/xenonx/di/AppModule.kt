package org.lotka.xenonx.di

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.repository.AuthScreenRepositoryImpl
import org.lotka.xenonx.data.repository.ProfileScreenRepositoryImpl
import org.lotka.xenonx.domain.repository.AuthScreenRepository
import org.lotka.xenonx.domain.repository.ProfileScreenRepository
import org.lotka.xenonx.domain.usecase.auth.AuthUseCases
import org.lotka.xenonx.domain.usecase.auth.IsUserAuthenticatedInFirebase
import org.lotka.xenonx.domain.usecase.auth.SignIn
import org.lotka.xenonx.domain.usecase.auth.SignUp
import org.lotka.xenonx.domain.usecase.profile.CreateOrUpdateProfileToFirebase
import org.lotka.xenonx.domain.usecase.profile.LoadProfileFromFirebase
import org.lotka.xenonx.domain.usecase.profile.ProfileScreenUseCases
import org.lotka.xenonx.domain.usecase.profile.SetUserStatusToFirebase
import org.lotka.xenonx.domain.usecase.profile.SignOut
import org.lotka.xenonx.domain.usecase.profile.UploadPictureToFirebase
import org.lotka.xenonx.domain.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorageInstance() = FirebaseStorage.getInstance()

    @Provides
    fun provideFirebaseDatabaseInstance() = FirebaseDatabase.getInstance()

//    @Provides
//    fun provideSharedPreferences(application: Application) =
//        application.getSharedPreferences("login", Context.MODE_PRIVATE)

    @Provides
    fun providesDataStore(application: Application) = application.dataStore

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
    ): AuthScreenRepository = AuthScreenRepositoryImpl(auth)

//    @Provides
//    fun provideChatScreenRepository(
//        auth: FirebaseAuth,
//        database: FirebaseDatabase
//    ): ChatScreenRepository = ChatScreenRepositoryImpl(auth, database)

    @Provides
    fun provideProfileScreenRepository(
        auth: FirebaseAuth,
        database: FirebaseDatabase,
        storage: FirebaseStorage
    ): ProfileScreenRepository = ProfileScreenRepositoryImpl(auth, database, storage)

//    @Provides
//    fun provideUserListScreenRepository(
//        auth: FirebaseAuth,
//        database: FirebaseDatabase
//    ): UserListScreenRepository = UserListScreenRepositoryImpl(auth, database)

    @Provides
    fun provideAuthScreenUseCase(authRepository: AuthScreenRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticatedInFirebase(authRepository),
        signIn = SignIn(authRepository),
        signUp = SignUp(authRepository)
    )

//    @Provides
//    fun provideChatScreenUseCase(chatScreenRepository: ChatScreenRepository) = ChatScreenUseCases(
//        blockFriendToFirebase = BlockFriendToFirebase(chatScreenRepository),
//        insertMessageToFirebase = InsertMessageToFirebase(chatScreenRepository),
//        loadMessageFromFirebase = LoadMessageFromFirebase(chatScreenRepository),
//        opponentProfileFromFirebase = LoadOpponentProfileFromFirebase(chatScreenRepository)
//    )

    @Provides
    fun provideProfileScreenUseCase(profileScreenRepository: ProfileScreenRepository) =
        ProfileScreenUseCases(
            createOrUpdateProfileToFirebase = CreateOrUpdateProfileToFirebase(
                profileScreenRepository
            ),
            loadProfileFromFirebase = LoadProfileFromFirebase(profileScreenRepository),
            setUserStatusToFirebase = SetUserStatusToFirebase(profileScreenRepository),
            signOut = SignOut(profileScreenRepository),
            uploadPictureToFirebase = UploadPictureToFirebase(profileScreenRepository)
        )

}