package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteBackgound
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts
import org.lotka.xenonx.presentation.theme.newPrimaryTextColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiToggleButton(
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDarkMode: Boolean
) {
    val selectedTint = if (isDarkMode) kilidPrimaryColor else kilidPrimaryColor


    FlowRow(
        modifier = modifier

    ) {
        toggleStates.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection.lowercase() == toggleState.lowercase()
            val unselectedTint = if (isDarkMode) kilidDarkBackgound else kilidWhiteBackgound
            val backgroundTint = if (isSelected) selectedTint else unselectedTint
            val textColor =
                if (isSelected) Color.White else if (isDarkMode) kilidDarkTexts else kilidWhiteTexts

            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .border(
                        BorderStroke(1.dp, Color.LightGray),
                        RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .background(backgroundTint)
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .toggleable(
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }
                        }
                    )
            ) {
                Text(
                    toggleState,
                    color = textColor,
                    modifier = Modifier.padding(2.dp),
                    style = KilidTypography.h2.copy(color = newPrimaryTextColor)
                )
            }
        }
    }
}