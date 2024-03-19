package org.lotka.xenonx.presentation.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.theme.KilidTypography


@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text, fontWeight = FontWeight.Bold,
        color = Color.Black, style = KilidTypography.h4, fontSize = 24.sp, modifier = modifier
    )
}

@Composable
fun TextFieldHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text, color = Color.Black,
        style = KilidTypography.h3, fontSize = 14.sp,
        modifier = modifier
    )
}