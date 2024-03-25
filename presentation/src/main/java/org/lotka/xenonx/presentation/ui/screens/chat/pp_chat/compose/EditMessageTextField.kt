package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import org.lotka.xenonx.presentation.theme.TelegramBackGround
import java.lang.reflect.Modifier

@Composable
fun EditMessageTextField(
    originalMessage: String,
    onEditMessage: (editedMessage: String) -> Unit
) {
    var editedMessage by remember { mutableStateOf(originalMessage) }

    TextField(
        value = editedMessage,
        onValueChange = { editedMessage = it },
//        modifier = Modifier.fillMaxWidth(),
        label = { Text("Edit Message") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = TelegramBackGround,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            textColor = Color.White
        ),
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onEditMessage(editedMessage)
            }
        )
    )
}