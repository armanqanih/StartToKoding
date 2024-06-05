package org.lotka.xenonx.presentation.ui.screens.plp.top_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.lotka.xenonx.presentation.R

import org.lotka.xenonx.presentation.theme.kilidPrimaryColor


@Composable
fun HomeTopBar(
    showAgency: Boolean = true,
    onClick: () -> Unit,
    mainScreens: Boolean = false,
    onBackPressed: () -> Unit,
    onToggleTheme: () -> Unit,
    isDarkMode: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(kilidPrimaryColor),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = if (isDarkMode) rememberAsyncImagePainter(R.drawable.dark_mode) else rememberAsyncImagePainter(R.drawable.light_mode),
            tint = Color.White,
            contentDescription = "agency",
            modifier = Modifier
                .clickable {
                    onToggleTheme()
                }
        )
        Spacer(modifier = Modifier.weight(1f))

        if (mainScreens) {
            Image(
                painter = painterResource(id = R.drawable.kilid_portal_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .align(CenterVertically)
            )
        } else {
            Box(
                Modifier
                    .width(80.dp)
                    .fillMaxHeight()
                    .align(CenterVertically)
                    .clickable {
                        onBackPressed()
                    },
                contentAlignment = Center
            ) {
                Spacer(modifier = Modifier.width(30.dp))
                Icon(
                    painter = rememberAsyncImagePainter(R.drawable.ic_arrow_left),
                    tint = Color.White,
                    contentDescription = "agency",
                    modifier = Modifier.size(18.dp)
                )
            }

        }
    }
}