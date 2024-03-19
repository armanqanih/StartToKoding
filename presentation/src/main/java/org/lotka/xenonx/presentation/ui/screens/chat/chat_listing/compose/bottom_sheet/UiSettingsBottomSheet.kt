package org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.compose.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.etc.NDDropDownMenu
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.newSecondaryColor

@ExperimentalMaterialApi
@Composable
fun UiSettingsBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    ) {


    val spaceBetweenButtons = 15.dp
    val padding = Modifier.padding(bottom = 8.dp, end = 4.dp, start = 8.dp)



        Column(
            modifier = Modifier
                .padding(0.dp)
                .background(White)
                .fillMaxWidth()
                .padding(20.dp)

        ) {

            Divider(
                Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally), color = newSecondaryColor, thickness = 8.dp
            )


            Spacer(modifier = Modifier.height(spaceBetweenButtons))

            Text(
                text = stringResource(id = R.string.ui_settings),
                style = KilidTypography.h5,

                )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.plp_choose_layout),
                    style = KilidTypography.h2,
                    modifier = Modifier
                        .weight(1f)
                )

                NDDropDownMenu(
                    modifier = Modifier
                        .weight(1f),
                    options = listOf("نمایش لیست با عکس بزرگ و جزئیات بیشتر", "نمایش لیست با حداقل جزئیات"),
                    selectedOption = "نمایش لیست با حداقل جزئیات",
                    onNewSelection = {

                    })
            }

            Divider()


            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.dark_mode),
                    style = KilidTypography.h2,
                    modifier = Modifier
                        .weight(1f)
                )

                NDDropDownMenu(
                    modifier = Modifier
                        .weight(1f),
                    options = listOf("حالت خودکار", "حالت شب", "حالت روز"),
                    selectedOption ="حالت خودکار",
                    onNewSelection = {

                    })
            }

            Divider()




        }




}

