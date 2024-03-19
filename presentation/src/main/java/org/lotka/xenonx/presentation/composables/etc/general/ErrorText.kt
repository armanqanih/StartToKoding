package org.lotka.xenonx.presentation.composables.etc.general

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorText(resource: Int, textAlign: TextAlign = TextAlign.End) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        text = stringResource(id = resource),
        color = MaterialTheme.colors.error,
        style = MaterialTheme.typography.caption,
        textAlign = textAlign
    )
}