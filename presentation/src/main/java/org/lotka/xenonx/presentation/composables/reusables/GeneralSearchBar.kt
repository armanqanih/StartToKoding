package org.lotka.xenonx.presentation.composables.reusables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidDarkBorders
import org.lotka.xenonx.presentation.theme.kilidDarkObjects
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteObjects
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts


@Composable
fun GeneralSearchBar(
    placeHolder: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    onValueRemoved: () -> Unit,
    onKeyboardActions: () -> Unit,
    onPerformSearch: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isRtl: Boolean = false,
    isDarkTheme: Boolean
) {

    val coroutineScope = rememberCoroutineScope()
    // Variable to keep track of the last input time
    var lastInputTime by remember { mutableStateOf(System.currentTimeMillis()) }

    // Function to handle the input change
    fun handleInputChange(newValue: String) {
        onValueChange(newValue)
        lastInputTime = System.currentTimeMillis()

        coroutineScope.launch {
            delay(2000) // Wait for 2 seconds
            if (System.currentTimeMillis() >= lastInputTime + 2000) {
                onPerformSearch()
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        OutlinedTextField(

            placeholder = {
                Text(
                    text = placeHolder,
                    textAlign = if (isRtl) TextAlign.Start else TextAlign.End,
                    color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts,
                    style = KilidTypography.h2,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            value = value,
            onValueChange = { newValue ->
                handleInputChange(newValue)
            },

            colors = TextFieldDefaults.textFieldColors(
                textColor = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts,
                focusedLabelColor = kilidPrimaryColor,
                backgroundColor = if (isDarkTheme) kilidDarkBackgound else White,
                cursorColor = kilidPrimaryColor,
                focusedIndicatorColor = kilidPrimaryColor,
                unfocusedIndicatorColor = if (isDarkTheme) kilidWhiteObjects else kilidDarkBorders,
            ),
            textStyle = KilidTypography.h2.copy(
                textAlign = (if (isRtl) TextAlign.Start else TextAlign.End),
                color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),

            keyboardActions = KeyboardActions(onAny = {
                onKeyboardActions()
            }
            ),
            trailingIcon = {

                if (value.isEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = if (isDarkTheme) kilidWhiteObjects else kilidDarkObjects,
                        contentDescription = "Search",
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Search",
                        tint = if (isDarkTheme) kilidWhiteObjects else kilidDarkObjects,
                        modifier = Modifier.clickable {
                            onValueRemoved()
                            onPerformSearch()
                        }
                    )
                }
            },
            modifier = modifier
        )
    }


}
