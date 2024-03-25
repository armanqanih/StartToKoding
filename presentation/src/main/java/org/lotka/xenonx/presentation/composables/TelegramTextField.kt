package org.lotka.xenonx.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TelegramTextField(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable () -> Unit,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.LightGray,
    textColor: Color = Color.Black,
    leadingIconColor: Color = Color.Gray
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
//            .padding(horizontal = 16.dp),
      , value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.body1.copy(color = textColor),
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primary,
            disabledLabelColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            disabledTextColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
            errorCursorColor = MaterialTheme.colors.error,
            errorIndicatorColor = MaterialTheme.colors.error,
            focusedLabelColor = MaterialTheme.colors.primary,
            placeholderColor = Color.Gray
        ),
        shape = shape,
        singleLine = true,

    )
}
