package org.lotka.xenonx.presentation.ui.screens.HomeScreen.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.etc.general.TextWithLeadingIcon
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.newGrayTextColor
import org.lotka.xenonx.presentation.theme.newSecondTextColor
import org.lotka.xenonx.presentation.theme.newSecondaryColor
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.PlpViewModel

@ExperimentalMaterialApi
@Composable
fun ListingItemBottomSheet(
    viewModel: PlpViewModel,
    modalBottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    val context = LocalContext.current


    val spaceBetweenButtons = 15.dp

    Column(
        modifier = Modifier
            .padding(0.dp)
            .background(White)
            .fillMaxWidth()
            .padding(20.dp)
            .padding(bottom = 46.dp)
    ) {

        Divider(
            Modifier
                .width(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally), color = newSecondaryColor, thickness = 8.dp
        )


        Spacer(modifier = Modifier.height(spaceBetweenButtons))


        //share
        TextWithLeadingIcon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()

                    }
                },
            text = stringResource(id = R.string.share),
            color = newSecondTextColor,
            textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
            icon = R.drawable.nd_share
        )


        Spacer(modifier = Modifier.height(spaceBetweenButtons))


        // occasion
        TextWithLeadingIcon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        modalBottomSheetState.hide()
//                        viewModel.onOccasionClicked(
//                            title = occasionDialogTitle,
//                            description = operationConfirmation
//                        )
                    }
                },
            text = stringResource(id = R.string.occasion),
            color = newSecondTextColor,
            textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
            icon = R.drawable.nd_star
        )


        Spacer(modifier = Modifier.height(spaceBetweenButtons))

        //archive
        TextWithLeadingIcon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
//                    coroutineScope.launch {
//                        modalBottomSheetState.hide()
//                        viewModel.onArchiveOrActiveClicked(
//                            title = if (!viewModel.selectedItem?.expired!!) archiveDialogTitle else unarchiveDialogTitle,
//                            description = operationConfirmation
//                        )
//                    }
                },
            text = "",
            color = newSecondTextColor,
            textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
            icon = R.drawable.nd_archive
        )


        Spacer(modifier = Modifier.height(spaceBetweenButtons))



        TextWithLeadingIcon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
//                        modalBottomSheetState.hide()
//                        viewModel.onLadderUpClicked(
//                            title = ladderUpDialogTitle,
//                            description = ladderUpDialogDescription
//                        )
                    }
                },
            text = stringResource(id = R.string.ladder_up),
            color = newSecondTextColor,
            textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
            icon = R.drawable.nd_ladderup
        )


        //edit
//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(42.dp),
//            shape = RoundedCornerShape(0.dp),
//            onClick = {
//                coroutineScope.launch {
//                    modalBottomSheetState.hide()
//                    viewModel.onEditClicked()
//                }
//            },
//            colors = ButtonDefaults.buttonColors(backgroundColor = White)
//        ) {
//            TextWithLeadingIcon(
//                modifier = Modifier.fillMaxWidth(),
//                text = stringResource(id = R.string.edit),
//                color = newSecondTextColor,
//                textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
//                icon = R.drawable.nd_edit_pencil
//            )
//        }


        Spacer(modifier = Modifier.height(spaceBetweenButtons))

        // remove
        TextWithLeadingIcon(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {


//                    coroutineScope.launch {
//                        modalBottomSheetState.hide()
//                        viewModel.onFileRemoveClicked(
//                            title = removeFileDialogTitle,
//                            description = removeFileDialogDescription
//                        )
//                    }
                },
            text = stringResource(id = R.string.remove_file),
            color = newSecondTextColor,
            textStyle = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
            icon = R.drawable.nd_trash
        )


        Spacer(modifier = Modifier.height(spaceBetweenButtons + 8.dp))


        //cancel

        TextButton(
            onClick = {
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }

            },
            modifier = Modifier
                .border(
                    width = 1.dp, color = newSecondTextColor, shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(46.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = kilidPrimaryColor
            ),
            shape = RoundedCornerShape(8.dp)

        ) {


            Text(
                text = stringResource(R.string.cancel),
                color = newGrayTextColor,
                style = MaterialTheme.typography.h3
            )

        }



        Spacer(modifier = Modifier.height(8.dp))

    }
}
