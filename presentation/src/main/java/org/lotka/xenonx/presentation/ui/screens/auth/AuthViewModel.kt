package org.lotka.xenonx.presentation.ui.screens.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.auth.AuthUseCases
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
//    private val profileScreenUseCases: ProfileScreenUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()


    init {
        isUserAuthenticated()
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
                            it.copy(isUserSignInState = result.data,
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
                            it.copy(isUserSignInState = result.data,
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
                            it.copy(isUserSignInState = result.data)
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

}