package org.lotka.xenonx.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.composables.etc.ConnectivityMonitor
import org.lotka.xenonx.presentation.composables.etc.DefaultSnackbar
import org.lotka.xenonx.presentation.composables.etc.GenericDialog
import org.lotka.xenonx.presentation.composables.etc.GenericDialogInfo
import java.util.Queue

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = PrimaryDark,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    primaryVariant = PrimaryDark,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */


    // new design colors


)


@Composable
fun KilidProTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = KilidTypography,
        shapes = Shapes,
        content = content
    )
}


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun AuthTheme(
    darkTheme: Boolean,
    isNetworkAvailable: Boolean,
    displayProgressBar: Boolean,
    scaffoldState: ScaffoldState,
    dialogQueue: Queue<GenericDialogInfo>? = null,
    content: @Composable () -> Unit,
) {


    MaterialTheme(
        colors = if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        },
        typography = KilidTypography,
        shapes = Shapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (!darkTheme) Color.Red else Color.Green)
        ) {
            content()




            ConnectivityMonitor(
                isNetworkAvailable = isNetworkAvailable,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 70.dp)
            )

            if (displayProgressBar) {
                LinearProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                )
            }


            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            ProcessDialogQueue(
                dialogQueue = dialogQueue,
                darkTheme = darkTheme
            )
        }
    }
}


@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericDialogInfo>?,
    darkTheme: Boolean
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction,
            darkTheme = darkTheme
        )
    }
}