package org.lotka.xenonx.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.theme.KilidTypography

@Composable
fun KilidBulleteItem(items: List<String>) {
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
    Text(
        buildAnnotatedString {
            items.forEach {
                withStyle(style = paragraphStyle) {
                    append(Typography.bullet)
                    append("\t\t")
                    append(it)
                }
            }
        },
        modifier = androidx.compose.ui.Modifier.padding(start = 16.dp).fillMaxWidth(),
        style = KilidTypography.h3
    )
}