package org.lotka.xenonx.presentation.composables.etc

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.theme.DarkGray

@ExperimentalMaterialApi
@Composable
fun KilidExposedDropDown(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedIndex: Int?,
    @StringRes label: Int,
    onDropDownClicked: (index: Int) -> Unit,
    isRequired: Boolean = false,
    isError: Boolean = false,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp)
) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value =
            if (!options.isNullOrEmpty()) {
                selectedIndex?.let {
                    options[selectedIndex]
                } ?: ""
            } else "",
            onValueChange = {

            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            label = {
                Text(
                    text = stringResource(id = label) + if (isRequired) " *" else "",
                    style = MaterialTheme.typography.h3
                )
            },
            singleLine = true,
            shape = shape,
            isError = isError
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEachIndexed { index, text ->
                DropdownMenuItem(
                    onClick = {
                        onDropDownClicked(index)
                        expanded = false
                    }
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.h3,
                        color = DarkGray
                    )
                }
            }
        }
    }
}