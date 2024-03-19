package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import org.lotka.xenonx.presentation.theme.KilidTypography


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun NDDropDownMenu(
    options: List<String>,
    selectedOption: String,
    onNewSelection: (String) -> Unit,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }
    // remember the selected item
    val selectedItem = selectedOption

    // box
    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
            keyboardController?.hide()
        }
    ) {
        // text field
        OutlinedTextField(

            value = selectedItem,
            textStyle = KilidTypography.h2,
            onValueChange = { selectedItem },
            readOnly = true,
            enabled = false,
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },

            )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            options.size
            options.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    onNewSelection(selectedOption)
                    expanded = false
                }) {
                    Text(text = selectedOption, style = KilidTypography.h2)
                }
            }
        }
    }
}