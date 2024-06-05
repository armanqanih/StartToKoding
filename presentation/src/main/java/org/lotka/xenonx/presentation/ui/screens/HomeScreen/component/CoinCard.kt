package org.lotka.xenonx.presentation.ui.screens.HomeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.sp

import org.lotka.xenonx.domain.model.CoinModel

import org.lotka.xenonx.presentation.theme.Green
import org.lotka.xenonx.presentation.theme.Red

@Composable
fun CoinCard(
    modifier: Modifier = Modifier,
    coin: CoinModel,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },
        horizontalArrangement = Arrangement.SpaceAround,

        ) {
            Text(
                text = "${coin.name} ${coin.symbol} ",
                style = androidx.compose.material.MaterialTheme.typography.body1,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = if (
                        coin.isActive
                    ) "is active" else "not active",
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                        ),
                    color = if (coin.isActive) Green else Red,
                )

    }
}


