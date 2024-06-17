package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.ainarm.chat.presentation.R

import org.lotka.xenonx.dataold.enums.SnackBarType

import org.lotka.xenonx.presentation.theme.DarkGreen
import org.lotka.xenonx.presentation.theme.DarkRed
import org.lotka.xenonx.presentation.theme.DarkYellow
import org.lotka.xenonx.presentation.theme.LightGreen
import org.lotka.xenonx.presentation.theme.SuperLightRed
import org.lotka.xenonx.presentation.theme.SuperLightYellow

@Composable
fun KilidSnackBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    text: String,
    onDismiss: () -> Unit,
    snackBarType: SnackBarType
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        Snackbar(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .height(56.dp),
            content = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(7f),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when (snackBarType) {
                            SnackBarType.SUCCESS -> Icon(
                                Icons.Filled.Check,
                                "Check",
                                modifier = Modifier.size(20.dp),
                                tint = DarkGreen
                            )

                            SnackBarType.ERROR -> Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info),
                                contentDescription = "Error",
                                tint = DarkRed
                            )

                            SnackBarType.WARNING -> Icon(
                                painter = painterResource(id = R.drawable.ic_outline_info),
                                contentDescription = "Warning",
                                tint = DarkYellow
                            )
                        }
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body2.copy(fontSize = 12.sp),
                            color = when (snackBarType) {
                                SnackBarType.SUCCESS -> DarkGreen
                                SnackBarType.ERROR -> DarkRed
                                SnackBarType.WARNING -> DarkYellow
                            }
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        onClick = { onDismiss() })
                    {
                        Icon(
                            Icons.Filled.Close,
                            "Close",
                            tint = when (snackBarType) {
                                SnackBarType.SUCCESS -> DarkGreen
                                SnackBarType.ERROR -> DarkRed
                                SnackBarType.WARNING -> DarkYellow
                            }
                        )
                    }
                }
            },
            backgroundColor = when (snackBarType) {
                SnackBarType.SUCCESS -> LightGreen
                SnackBarType.ERROR -> SuperLightRed
                SnackBarType.WARNING -> SuperLightYellow
            },
            contentColor = when (snackBarType) {
                SnackBarType.SUCCESS -> DarkGreen
                SnackBarType.ERROR -> DarkRed
                SnackBarType.WARNING -> DarkYellow
            },
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp
        )
    }
}