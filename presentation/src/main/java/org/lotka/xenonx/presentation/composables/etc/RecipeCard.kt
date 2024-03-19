package org.lotka.xenonx.presentation.composables.etc

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.lotka.xenonx.domain.model.model.plp.PlpItemResultModel
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.theme.KilidTypography

@ExperimentalCoroutinesApi
@Composable
fun RecipeCard(
    recipe: PlpItemResultModel,
    onClick: () -> Unit,
) {
    Column {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(
                        if (recipe.userLastName == "PRO_ANDROID") {
                            R.drawable.android
                        } else {
                            R.drawable.android
                        }
                    )
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .border(width = 1.dp, color = Color.Gray, CircleShape)
                    .padding(7.dp),
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Row {
                    Text(text = "نام دستگاه :  ", style = KilidTypography.h3)


                }

                Row {
                    Text(text = "آدرس اینترنتی :  ", style = KilidTypography.h3)
                    Text(
                        text = recipe.numUnreadMessage.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h4,
                        maxLines = 1
                    )

                }

                Row {
                    Text(text = "آخرین زمان اتصال به سامانه : ", style = KilidTypography.h3)
                    Text(
                        text = recipe.isPremiumUser.toString(),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h4,
                        maxLines = 1
                    )

                }


            }


        }
        Divider(modifier = Modifier.padding(vertical = 12.dp))
    }
}


