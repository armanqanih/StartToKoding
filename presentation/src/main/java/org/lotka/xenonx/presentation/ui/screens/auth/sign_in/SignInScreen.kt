package org.lotka.xenonx.presentation.ui.screens.auth.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PlayArrow

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.ainarm.chat.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.BottomNavItem
import org.lotka.xenonx.presentation.ui.screens.auth.AuthEvent
import org.lotka.xenonx.presentation.ui.screens.auth.AuthViewModel
import org.lotka.xenonx.presentation.ui.screens.auth.compose.BottomRouteSign
import org.lotka.xenonx.presentation.ui.screens.auth.compose.ButtonSign
import org.lotka.xenonx.presentation.ui.screens.auth.compose.LoginEmailCustomOutlinedTextField
import org.lotka.xenonx.presentation.ui.screens.auth.compose.LoginPasswordCustomOutlinedTextField
import org.lotka.xenonx.presentation.ui.screens.auth.compose.TextLightweight
import org.lotka.xenonx.presentation.util.SnackbarController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    emailFromSignUp: String,
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController
) {
    val state = authViewModel.state
    //Set SnackBar
    val snackebar = state.collectAsState().value.showSnakeBar
    LaunchedEffect(key1 = snackebar) {
        if (snackebar != "") {
            SnackbarController(this).showSnackbar(snackbarHostState, snackebar, "Close")
        }
    }

    //For test user information
    var textEmail: String? by remember { mutableStateOf("") }//gimli@gmail.com
    var textPassword: String? by remember { mutableStateOf("") }//123456

    LaunchedEffect(key1 = Unit) {
       authViewModel.onEvent(AuthEvent.EmailChanged(emailFromSignUp))
    }

    //Check User Authenticated
    val isUserAuthenticated = state.collectAsState().value.isUserAuthenticatedState
    LaunchedEffect(Unit) {
        if (isUserAuthenticated == true) {
            navController.navigate(BottomNavItem.UserList.fullRoute)
        }
    }

    //Sign In Navigate
    val isUserSignIn = state.collectAsState().value.isUserSignIn
    LaunchedEffect(key1 = isUserSignIn) {
        if (isUserSignIn == true) {
            keyboardController.hide()
            navController.navigate(BottomNavItem.Profile.fullRoute)
        }
    }
    Column {
        Surface(
            modifier = Modifier
                .weight(8f)
                .fillMaxSize()
                .focusable(true)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        keyboardController.hide()
                    })
                }
                .padding(12.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = null
                )
                TextLightweight()

                Box(modifier = Modifier.padding(top =8.dp)) {
                    LoginEmailCustomOutlinedTextField(textEmail!!, "Email", Icons.Default.Email) {
                        textEmail = it
                    }
                }

                Box(modifier = Modifier.padding(top = 8.dp)) {
                    LoginPasswordCustomOutlinedTextField(
                        textPassword!!,
                        "Password",
                       Icons.Filled.PlayArrow
                    ) {
                        textPassword = it
                    }
                }

                ButtonSign(
                    onclick = {
                        authViewModel.signIn(textEmail!!, textPassword!!)
                    },
                    signInOrSignUp = "Sign In"
                )
            }
        }
        BottomRouteSign(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .focusable(true)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { keyboardController.hide() })
                },
            onclick = {
                if (textEmail == "") {
                    navController.popBackStack()
                    navController.navigate(BottomNavItem.SignUp.fullRoute)
                } else {
                    navController.popBackStack()
                    navController.navigate(BottomNavItem.SignUp.screen_route + "?emailFromSignIn=$textEmail")
                }
            },
            signInOrSignUp = "Sign Up",
            label = "Don't have an account?"
        )
    }
}