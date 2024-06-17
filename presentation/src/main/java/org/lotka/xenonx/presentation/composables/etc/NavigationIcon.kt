package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.ainarm.chat.presentation.R


import org.lotka.xenonx.presentation.theme.White

@Composable
fun NavigationIcon(canPop: Boolean, navController: NavController) {
    if (canPop) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = White
            )
        }
    }
}