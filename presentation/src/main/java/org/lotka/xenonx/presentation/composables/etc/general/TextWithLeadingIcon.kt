package org.lotka.xenonx.presentation.composables.etc.general

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextWithLeadingIcon(
    modifier: Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Justify,
    color: Color = MaterialTheme.colors.onPrimary,
    textStyle: TextStyle = LocalTextStyle.current,
    layoutDirection: androidx.compose.ui.unit.LayoutDirection = androidx.compose.ui.unit.LayoutDirection.Rtl,
    icon: Int,
    contentDescription: String = "icon"
) {
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                color = color,
                textAlign = textAlign,
                style = textStyle
            )
        }
    }
}