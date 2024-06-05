package org.lotka.xenonx.presentation.ui.screens.plp

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import org.lotka.xenonx.domain.model.PlpItemResultModel
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.FastImage
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidDarkBorders
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidWhiteBackgound
import org.lotka.xenonx.presentation.theme.kilidWhiteBorders
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts

@Composable
fun PlpItem(
    isDarkTheme : Boolean,
    item: PlpItemResultModel?,
    screen: Configuration,
    onMoreClicked : (id:Int) -> Unit,
    onClicked : (id:Int) -> Unit,
    onLadderUpClick : (id:Int) -> Unit,
    onFeaturedClick : (id:Int) -> Unit,
    index : Int
) {

    val isFeatured = item?.featured
    Card(
        modifier = Modifier
            .padding(4.dp)
            .background(if (isDarkTheme) kilidDarkBackgound else kilidWhiteBackgound)
            .clickable { onClicked(item?.id!!) },
        shape = RoundedCornerShape(8.dp) ,
        border = BorderStroke( 1.dp  , if (isDarkTheme) kilidDarkBorders else kilidWhiteBorders ),
        elevation = 0.dp) {


        val thumbnailUrl: String = item?.coverPicture ?: "string"
        val thumbnailDrawable: Int? = if (thumbnailUrl == "string") R.drawable.nd_noimage else null

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(if (isDarkTheme) kilidDarkBackgound else kilidWhiteBackgound)
        ) {


            Box(
                modifier = Modifier
                    .background(Color.White)
                    .size(170.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                // Image
                FastImage(
                    imageUrl = thumbnailUrl ?: thumbnailDrawable,
                    contentDescription = "Plp Item Image",
                    modifier = Modifier
                        .background(Color.White)
                        .size(170.dp)
                )

                // Text
                if (item?.featured == true){
                    Text(
                        text = "Featured",
                        style = KilidTypography.h5.copy(fontSize = 16.sp , color = Color.White),
                        modifier = Modifier
                            .background(Color.Black.copy(alpha = 0.6f))
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                            .alpha(0.6f)
                        ,                    textAlign = TextAlign.Center
                    )
                }
            }

            // Advertisement Info
            Column(
                modifier = Modifier
                    .weight(2f)
                    .height(170.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                //title
                Text(
                    text = item?.title ?: "",
                    style = MaterialTheme.typography.h3.copy(fontSize = 14.sp),
                    color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 5.dp , start = 8.dp , end = 2.dp, bottom = 0.dp)
                )

                Column(Modifier.padding(top = 0.dp , start = 8.dp , end = 2.dp, bottom = 0.dp)) {






                }


            }
        }
    }

}









