package org.lotka.xenonx.presentation.ui.screens.chat.user_list.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AlertDialogChat(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val dialogText = "Add user via email".trimIndent()
    var emailInput by remember {
        mutableStateOf("")
    }
    AlertDialog(
//        buttons = {
//               Icon(imageVector = Icons.Filled.Person, contentDescription = null)
//        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Add User",
                textAlign = TextAlign.Center
            )
        },

        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(emailInput)
                }
            ) {
                Text(text = "OK")
            }
        },

        text = {
            AlertDialogCustomOutlinedTextField(
                entry = emailInput,
                hint = "email",
                onChange = { emailInput = it })
        }
    )
}