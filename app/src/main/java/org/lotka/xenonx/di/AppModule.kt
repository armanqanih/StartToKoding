package org.lotka.xenonx.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import org.lotka.xenonx.BaseAppController
import org.lotka.xenonx.data.repository.user.UserListRepositoryImpl
import org.lotka.xenonx.data.repository.user.UserRemoteDataSource
import org.lotka.xenonx.domain.repository.UserListRepository
import org.lotka.xenonx.domain.usecase.user.AcceptPendingFriendRequestToFirebase
import org.lotka.xenonx.domain.usecase.user.CheckChatRoomExistedFromFirebase
import org.lotka.xenonx.domain.usecase.user.CheckFriendListRegisterIsExistedFromFirebase
import org.lotka.xenonx.domain.usecase.user.CreateChatRoomToFirebase
import org.lotka.xenonx.domain.usecase.user.CreateFriendListRegisterToFirebase
import org.lotka.xenonx.domain.usecase.user.LoadAcceptedFriendRequestListFromFirebase
import org.lotka.xenonx.domain.usecase.user.LoadPendingFriendRequestListFromFirebase
import org.lotka.xenonx.domain.usecase.user.OpenBlockedFriendToFirebase
import org.lotka.xenonx.domain.usecase.user.RejectPendingFriendRequestToFirebase
import org.lotka.xenonx.domain.usecase.user.SearchUserFromFirebase
import org.lotka.xenonx.domain.usecase.user.UserListScreenUseCases
import org.lotka.xenonx.domain.util.Constants
import org.lotka.xenonx.presentation.util.DispatchersProvider
import org.lotka.xenonx.presentation.util.DispatchersProviderImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//  @Provides
//  @Singleton
//  fun FirebaseAuth(): FirebaseAuth = Firebase.auth
//




    @Singleton
    @Provides
    fun provideUserListScreenRepository(
      datastore:UserRemoteDataSource
    ): UserListRepository = UserListRepositoryImpl(datastore)


    @Singleton
    @Provides
    fun provideUserListScreenUseCase(userListScreenRepository: UserListRepository) =
        UserListScreenUseCases(
            acceptPendingFriendRequestToFirebase = AcceptPendingFriendRequestToFirebase(
                userListScreenRepository
            ),
            checkChatRoomExistedFromFirebase = CheckChatRoomExistedFromFirebase(
                userListScreenRepository
            ),
            checkFriendListRegisterIsExistedFromFirebase = CheckFriendListRegisterIsExistedFromFirebase(
                userListScreenRepository
            ),
            createChatRoomToFirebase = CreateChatRoomToFirebase(userListScreenRepository),
            createFriendListRegisterToFirebase = CreateFriendListRegisterToFirebase(
                userListScreenRepository
            ),
            loadAcceptedFriendRequestListFromFirebase = LoadAcceptedFriendRequestListFromFirebase(
                userListScreenRepository
            ),
            loadPendingFriendRequestListFromFirebase = LoadPendingFriendRequestListFromFirebase(
                userListScreenRepository
            ),
            openBlockedFriendToFirebase = OpenBlockedFriendToFirebase(userListScreenRepository),
            rejectPendingFriendRequestToFirebase = RejectPendingFriendRequestToFirebase(
                userListScreenRepository
            ),
            searchUserFromFirebase = SearchUserFromFirebase(userListScreenRepository),
        )






    @Provides
    @Singleton
    fun FirebaseAuthInstance(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return  FirebaseDatabase.getInstance("https://chat-xenonx-lotka-org.europe-west1.firebasedatabase.app/").reference.database
    }

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return Firebase.storage

    }


    @Provides
    @Singleton
    fun resources(application: Application): Resources = application.resources

    @Provides
    @Singleton
    fun context(application: Application): Context = application


    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun dispatcher(dispatchersProvider: DispatchersProviderImpl): DispatchersProvider =
        dispatchersProvider.dispatchersProvider


    @Provides
    @Singleton
    fun apiExceptionHandler(
        gson: Gson,
        sharedPreferences: SharedPreferences
    ): org.lotka.xenonx.data.exceptions.NetworkExceptionHandler =
        org.lotka.xenonx.data.exceptions.NetworkExceptionHandler(gson)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(Constants.TAG, Context.MODE_PRIVATE)
    }


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) =
        app as BaseAppController


    @ActivityScoped
    @Provides
    fun provideActivity(@ActivityContext activityContext: Context) = activityContext


    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
        val analytics = FirebaseAnalytics.getInstance(context)
        analytics.setAnalyticsCollectionEnabled(true)
        return analytics
    }


//    @Singleton
//    @Provides
//    fun provideUpdateManager(
//        @ApplicationContext context: Context,
//        analytics: FirebaseAnalytics
//    ): CustomUpdateManager {
//        return CustomUpdateManager(context, analytics)
//    }
}