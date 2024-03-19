package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.theme.Gray
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.newPrimaryTextColor
import org.lotka.xenonx.presentation.theme.newSecondaryColor


@Composable
fun MobinButton(
    title: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .height(40.dp)
        .border(BorderStroke(width = 2.dp, color = kilidPrimaryColor), RoundedCornerShape(8.dp)),
    //.border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(8.dp)),
    onClick: () -> Unit = {},
    loading: Boolean = false,
    outline: Boolean = false,
    enabled: Boolean = true,
    painter: Painter? = null,
    imageTint: Color = Color.Transparent

) {


    TextButton(
        onClick = {
            onClick()
        },
        enabled = enabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (outline) White else kilidPrimaryColor,
            disabledBackgroundColor = Gray,
            disabledContentColor = White,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        when (loading) {
            true -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    strokeCap = androidx.compose.ui.graphics.StrokeCap.Square,
                    strokeWidth = 3.dp,
                    color = if (outline) newSecondaryColor else White,
                )
            }

            false -> {

                if (painter != null) {
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painter,
                        contentDescription = "image",
                        modifier = Modifier.align(Alignment.CenterVertically).padding(start = 12.dp)
                            .size(25.dp),
                        colorFilter = if (imageTint != Color.Transparent) ColorFilter.tint(imageTint) else null
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    color = if (outline) newPrimaryTextColor else Color.White,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    style = KilidTypography.h3
                )
                Spacer(modifier = Modifier.width(6.dp))


            }
        }
    }
}
