package org.lotka.xenonx.presentation.ui.navigation

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}

//this is 0 by default
// when user call it for first time it will increase by one
// if after 3 second function called it will increase to 2
// if not it will decreased bt 1 to zero
var exit = 0
var handler: Handler? = null
var runnable: Runnable? = null

fun onBackPressedFunctionToFinishApp(context: Context) {
    exit += 1
    if (exit == 1) {
        Toast.makeText(context, "جهت خروج مجددا دکمه عقب را بزنید", Toast.LENGTH_SHORT).show()
        handler = Handler()
        runnable = Runnable {
            exit = 0
        }
        // Delay for 3 seconds
        handler?.postDelayed(runnable!!, 3000)
    } else if (exit > 1) {
        // Close the app
        (context as Activity).finish()
    }
}
