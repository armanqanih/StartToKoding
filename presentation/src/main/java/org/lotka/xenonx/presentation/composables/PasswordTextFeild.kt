package org.lotka.xenonx.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidDarkBorders
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts


@ExperimentalComposeUiApi
@Composable
fun PasswordTextField(
    value: String,
    @StringRes label: Int,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    isErrorMessage: String,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        placeholder = {
            Text(
                text = stringResource(id = label),
                textAlign = TextAlign.Start,
                color = kilidWhiteTexts,
                style = KilidTypography.h2,
            )
        },
        singleLine = true,
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility },
                modifier = Modifier.clickable { }
            ) {

                val imageResourceId =
                    if (passwordVisibility) R.drawable.ic_visibility else R.drawable.ic_visibility_off

                Icon(
                    painter = painterResource(id = imageResourceId),
                    contentDescription = "password visibility"
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = kilidWhiteTexts,
            focusedLabelColor = kilidPrimaryColor,
            backgroundColor = White,
            cursorColor = kilidPrimaryColor,
            focusedIndicatorColor = kilidPrimaryColor,
            unfocusedIndicatorColor = kilidDarkBorders,
            errorLabelColor = if (isError) Color.Red else kilidPrimaryColor,
        ),
        shape = RoundedCornerShape(8.dp),
        textStyle = KilidTypography.h2.copy(textAlign = TextAlign.Start, color = kilidWhiteTexts),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
    if (isError) {
        Text(
            text = isErrorMessage,
            color = Color.Red,
            style = KilidTypography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}