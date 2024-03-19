package org.lotka.xenonx.presentation.composables.etc

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.theme.PrimaryLight
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor

@Composable
fun ToolbarText(title: String, canPop: Boolean, navController: NavController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-34).dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = title, style = MaterialTheme.typography.h3.copy(color = White))
            }
        },
        navigationIcon = { NavigationIcon(canPop, navController) },
        backgroundColor = kilidPrimaryColor,
    )
}

@Composable
fun ToolbarImage(canPop: Boolean, navController: NavController) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = (-34).dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_kilid_pro_new_logo),
                    contentDescription = "Kilid pro"
                )
            }
        },
        navigationIcon = { NavigationIcon(canPop, navController) },
        backgroundColor = White,
    )
}

@Composable
fun ToolbarTextWithActionButton(
    title: String,
    @DrawableRes icon: Int? = null,
    canPop: Boolean,
    navController: NavController,
    onIconClicked: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-34).dp),
                    text = title
                )
                icon?.let {
                    IconButton(modifier = Modifier
                        .align(Alignment.CenterEnd),
                        onClick = { onIconClicked() }) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = title,
                            tint = PrimaryLight
                        )
                    }
                }
            }
        },
        navigationIcon = { NavigationIcon(canPop, navController) },
        backgroundColor = White,
    )
}