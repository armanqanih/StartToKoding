package org.lotka.xenonx.presentation.ui.screens.auth.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.ainarm.chat.presentation.R
import org.lotka.xenonx.presentation.ui.navigation.BottomNavItem
import org.lotka.xenonx.presentation.ui.screens.auth.AuthViewModel
import org.lotka.xenonx.presentation.ui.screens.auth.compose.BottomRouteSign
import org.lotka.xenonx.presentation.ui.screens.auth.compose.ButtonSign
import org.lotka.xenonx.presentation.ui.screens.auth.compose.LoginEmailCustomOutlinedTextField
import org.lotka.xenonx.presentation.ui.screens.auth.compose.LoginPasswordCustomOutlinedTextField
import org.lotka.xenonx.presentation.ui.screens.auth.compose.TextLightweight
import org.lotka.xenonx.presentation.util.SnackbarController


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    emailFromSignIn: String,
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController
) {
     val state = authViewModel.state
    //Set SnackBar
    val showSnackBar = state.collectAsState().value.showSnakeBar
    LaunchedEffect(key1 = showSnackBar) {
        if (showSnackBar != "") {
            SnackbarController(this).showSnackbar(snackbarHostState, showSnackBar, "Close")
        }
    }

    //For test user information
    var textEmail: String? by remember { mutableStateOf("") }//gimli@gmail.com
    var textPassword: String? by remember { mutableStateOf("") }//123456
    LaunchedEffect(key1 = Unit) {
        textEmail = emailFromSignIn
    }

    //Sign Up Navigate
    val isUserSignUp = state.collectAsState().value.isUserSignUp
    LaunchedEffect(key1 = isUserSignUp) {
        if (isUserSignUp == true) {
            keyboardController.hide()
            navController.navigate(BottomNavItem.Profile.fullRoute)
        }
    }

    //Compose Components
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
                .padding(12.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.chat),
                    contentDescription = null
                )
                TextLightweight()

                Box(modifier = Modifier.padding(top = 12.dp)) {
                    LoginEmailCustomOutlinedTextField(textEmail!!, "Email", Icons.Default.Email) {
                        textEmail = it
                    }
                }
                Box(modifier = Modifier.padding(top = 8.dp)) {
                    LoginPasswordCustomOutlinedTextField(
                        textPassword!!,
                        "Password",
                        Icons.Default.Close
                    ) {
                        textPassword = it
                    }
                }

                ButtonSign(
                    onclick = {
                        authViewModel.signUp(textEmail!!, textPassword!!)
                    },
                    signInOrSignUp = "Sign Up"
                )
            }
        }

        BottomRouteSign(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .focusable(true)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { keyboardController.hide() })
                },
            onclick = {
                if (textEmail == "") {
                    navController.popBackStack()
                    navController.navigate(BottomNavItem.SignIn.fullRoute)
                } else {
                    navController.popBackStack()
                    navController.navigate(BottomNavItem.SignIn.screen_route + "?emailFromSignUp=$textEmail")
                }
            },
            signInOrSignUp = "Sign In",
            label = "Already have an account?"
        )
    }
}