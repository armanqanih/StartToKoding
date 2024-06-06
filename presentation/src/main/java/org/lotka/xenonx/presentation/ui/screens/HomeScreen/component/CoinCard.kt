package org.lotka.xenonx.presentation.ui.screens.HomeScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.unit.sp

import org.lotka.xenonx.domain.model.CoinModel

import org.lotka.xenonx.presentation.theme.Green
import org.lotka.xenonx.presentation.theme.Magenta
import org.lotka.xenonx.presentation.theme.MunsellGreen
import org.lotka.xenonx.presentation.theme.PrimaryDark
import org.lotka.xenonx.presentation.theme.Red
import org.lotka.xenonx.presentation.theme.Yellow

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
                text = "${coin.name} ${coin.symbol} ${coin.rank} ",
                style = androidx.compose.material.MaterialTheme.typography.body1,
                color = when (coin.rank) {
                    1 -> Green
                    2 -> PrimaryDark
                    3 -> Magenta
                    else -> Color.Black // default color if rank is not 1 or 2
                },
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


