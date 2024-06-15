package org.lotka.xenonx.presentation.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwithme.domain.model.User
import com.example.chatwithme.domain.model.UserStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.auth.AuthUseCases
import org.lotka.xenonx.domain.usecase.profile.ProfileScreenUseCases
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val profileUseCases: ProfileScreenUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    

    init { isUserAuthenticated() }




  fun onEvent(event: AuthEvent){
      when(event){
          is AuthEvent.EmailChanged -> {
              _state.update { it.copy(email = event.email) }
          }
          is AuthEvent.PasswordChanged ->{
              _state.update { it.copy(password = event.password) }
          }
          is AuthEvent.ShowSnackBar -> {
              _state.update { it.copy(showSnakeBar = event.message) }
          }
          AuthEvent.SignIn -> {
              signIn(state.value.email, state.value.password)
          }
          AuthEvent.SignOut -> {
              setUserStatusToFirebase(UserStatus.OFFLINE)
          }
          AuthEvent.SignUp -> {
              signUp(state.value.email, state.value.password)
          }
      }
  }



    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.signIn(email, password).collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _state.update {
                            it.copy(showSnakeBar = "")}
                    }

                    is ResultState.Success -> {
                        setUserStatusToFirebase(UserStatus.ONLINE)
                        _state.update {
                            it.copy(isUserSignIn = result.data,
                                showSnakeBar = "Login Successful")
                                }
                    }
                    is ResultState.Error -> {
                      _state.update {
                          it.copy(showSnakeBar = "Login Failed" )
                      }
                    }
                }
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.signUp(email, password).collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                     _state.update { it.copy(showSnakeBar = "") }
                    }
                    is ResultState.Success -> {
                        _state.update {
                            it.copy(isUserSignUp = result.data,
                                showSnakeBar = "Login Successful")
                        }
                        firstTimeCreateProfileToFirebase()
                    }
                    is ResultState.Error -> {
                        try {
                            _state.update {
                                it.copy(showSnakeBar = "Login Failed" )
                            }
                        }catch (e: Exception){
                            Log.e("TAG", "signUp: ", Throwable(e))
                        }
//                        Timber.tag("TAG").e("signUp: ")
                    }
                }
            }
        }
    }

    private fun isUserAuthenticated() {
        viewModelScope.launch {
            authUseCases.isUserAuthenticated().collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _state.update {
                            it.copy(showSnakeBar = "")}

                    }
                    is ResultState.Success -> {
                        _state.update {
                            it.copy(isUserAuthenticatedState = result.data)
                        }
                        if (result.data == true) {
                            setUserStatusToFirebase(UserStatus.ONLINE)
                        }
                    }
                    is ResultState.Error -> {}
                }
            }
        }
    }

    private fun setUserStatusToFirebase(userStatus: UserStatus) {
        viewModelScope.launch {
            profileUseCases.setUserStatusToFirebase(userStatus).collect { response ->
                when (response) {
                    is ResultState.Loading -> {}
                    is ResultState.Success -> {}
                    is ResultState.Error -> {}
                }
            }
        }
    }

    private fun firstTimeCreateProfileToFirebase() {
        viewModelScope.launch {
            profileUseCases.createOrUpdateProfileToFirebase(User()).collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _state.update {
                            it.copy(showSnakeBar = "")}
                    }
                    is ResultState.Success -> {
                        if (result.data == true) {
                            _state.update {
                                it.copy(showSnakeBar = "Profile Updated")}

                        } else {
                            _state.update {
                                it.copy(showSnakeBar = "Profile Saved")}
                        }}
                    is ResultState.Error -> {
                        _state.update {
                            it.copy(showSnakeBar = "Update Failed")}
                    }}
            }
        }
    }
}