package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.theme.Gray
import org.lotka.xenonx.presentation.theme.White

@Composable
fun KilidButton(
    modifier: Modifier,
    onButtonClicked: () -> Unit,
    text: String,
    textColor: Color = White,
    loading: Boolean,
    enabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(corner = CornerSize(8.dp)),
    backgroundColor:
    Color = MaterialTheme.colors.primary,
    icon: Int? = null
) {
    Button(
        modifier = modifier.height(48.dp),
        onClick = onButtonClicked,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = Gray,
            contentColor = textColor,
            disabledContentColor = White
        )
    ) {
        when (loading) {
            true -> CircularProgressIndicator(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp),
                color = White
            )

            false -> {
                icon?.let {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "icon"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.h5.copy(
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun KilidButtonPreview() {
    KilidButton(
        modifier = Modifier.fillMaxWidth(),
        onButtonClicked = {},
        text = "Continue",
        loading = true
    )
}