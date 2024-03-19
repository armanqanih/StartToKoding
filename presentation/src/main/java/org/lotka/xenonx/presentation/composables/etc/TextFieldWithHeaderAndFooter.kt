package org.lotka.xenonx.presentation.composables.etc

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.etc.general.ErrorText
import org.lotka.xenonx.presentation.theme.GrayLight
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor

@Composable
fun TextFieldWithHeaderAndFooter(
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(),
    title: String? = null,
    titleModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 19.dp, vertical = 6.dp),
    value: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    footer: String? = null,
    footerModifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    isPassword: Boolean = false,
    singeLine: Boolean = false,
    onImeActionClicked: () -> Unit = {},
    isError: Boolean = false,
    footnote: String = "",
    @StringRes error: Int? = null,
) {


    var isPasswordVisible by remember { mutableStateOf(false) }


    if (!title.isNullOrEmpty()) {

        Text(
            text = title,
            style = KilidTypography.h4,
            modifier = titleModifier
        )

    }



    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        singleLine = singeLine,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = White,
            cursorColor = kilidPrimaryColor,
            focusedIndicatorColor = kilidPrimaryColor,
            unfocusedIndicatorColor = GrayLight,
        ),
        modifier = modifier,
        visualTransformation = if (isPassword) {
            if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        textStyle = MaterialTheme.typography.h3,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        trailingIcon = if (isPassword) {

            if (isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility
            {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_visibility_off) else painterResource(
                            id = R.drawable.ic_visibility
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp),
                        contentDescription = if (isPasswordVisible) "Visibility" else "Visibility Off",
                    )
                }
            }
        } else {
            null
        },
        isError = isError,
        enabled = enabled
    )

    when (error) {
        null -> {
            if (!footnote.isNullOrBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = footnote,
                    style = MaterialTheme.typography.caption.copy(fontSize = 12.sp),
                    textAlign = TextAlign.Start
                )
            }
        }

        else -> ErrorText(resource = error, textAlign = TextAlign.Start)
    }



    if (!footer.isNullOrEmpty()) {
        Text(
            text = footer,
            style = MaterialTheme.typography.h4,
            modifier = footerModifier
        )
    }


}