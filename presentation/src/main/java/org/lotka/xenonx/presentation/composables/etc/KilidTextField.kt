package org.lotka.xenonx.presentation.composables.etc

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.extension.addThousandsSeparator
import org.lotka.xenonx.presentation.extension.isValidPriceOrThrowError
import org.lotka.xenonx.presentation.extension.removeThousandsSeparator
import org.lotka.xenonx.presentation.composables.etc.general.ErrorText
import org.lotka.xenonx.presentation.theme.Red


@ExperimentalComposeUiApi
@Composable
fun KilidTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    value: String,
    @StringRes label: Int,
    isError: Boolean = false,
    @StringRes error: Int? = null,
    singleLine: Boolean = true,
    leadingIcon: ImageVector? = null,
    leadingIconDescription: String? = null,
    trailingIcon: ImageVector? = null,
    trailingIconDescription: String? = null,
    onTrailingIconClicked: () -> Unit = {},
    trailingIconVisibility: TrailingIconVisibility = TrailingIconVisibility.VISIBLE,
    footnote: String = "",
    footnoteFontSize: TextUnit = 10.sp,
    shape: Shape = RoundedCornerShape(8.dp),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onImeActionClicked: () -> Unit = {},
    isRequired: Boolean = false,
    enabled: Boolean = true,
    color: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    textColor: Color = MaterialTheme.typography.caption.color,
    fontSize: TextUnit = 13.sp
) {
    OutlinedTextField(
        modifier = modifier.navigationBarsPadding().imePadding(),
        value = value,
        onValueChange = onTextChanged,
        label = {
            Text(
                text = stringResource(id = label) + if (isRequired) " *" else "",
                style = MaterialTheme.typography.caption.copy(
                    fontSize = 13.sp
                )
            )
        },
        colors = color,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType).copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions { onImeActionClicked },
        leadingIcon = leadingIcon?.let {
            {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = leadingIconDescription
                    )
                }
            }
        },
        trailingIcon = trailingIcon?.let {
            when (trailingIconVisibility) {
                TrailingIconVisibility.VISIBLE -> {
                    {
                        IconButton(onClick = onTrailingIconClicked) {
                            Icon(
                                imageVector = it,
                                contentDescription = trailingIconDescription
                            )
                        }
                    }
                }

                TrailingIconVisibility.ONLY_WHEN_INPUT_IS_NOT_NULL -> {
                    if (!value.isNullOrBlank()) {
                        {
                            IconButton(onClick = onTrailingIconClicked) {
                                Icon(
                                    imageVector = it,
                                    contentDescription = trailingIconDescription
                                )
                            }
                        }
                    } else null
                }
            }
        },
        singleLine = singleLine,
        shape = shape,
        textStyle = MaterialTheme.typography.caption.copy(
            color = if (isError && !enabled) Red else textColor,
            fontSize = fontSize
        ),
        isError = isError,
        enabled = enabled,
        visualTransformation = visualTransformation
    )
    when (error) {
        null -> {
            if (!footnote.isNullOrBlank()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = footnote,
                    style = MaterialTheme.typography.caption.copy(fontSize = footnoteFontSize),
                    textAlign = TextAlign.Start
                )
            }
        }

        else -> ErrorText(resource = error, textAlign = TextAlign.Start)
    }
}


// format long to 123,456,789,9
class NumberCommaTransformation : VisualTransformation {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(if (text.text.isValidPriceOrThrowError() == null) text.text.formatWithComma() else text.text),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if (text.text.isValidPriceOrThrowError() == null) text.text.formatWithComma().length else 0
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}

fun String?.formatWithComma(): String {
    return this?.removeThousandsSeparator()?.addThousandsSeparator() ?: ""
}

enum class TrailingIconVisibility {
    VISIBLE,
    ONLY_WHEN_INPUT_IS_NOT_NULL
}
