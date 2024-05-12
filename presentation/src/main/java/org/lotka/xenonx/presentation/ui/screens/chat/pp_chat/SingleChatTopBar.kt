package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.domain.enums.IsOnlineStatus
import org.lotka.xenonx.presentation.composables.FastImage
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.OnlineTextColor
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidWhiteBackgound

@Composable
fun SingleChatTopBar(
    showAgency: Boolean = true,
    mainScreens: Boolean = false,
    onBackPressed: () -> Unit,
    onToggleTheme: () -> Unit,
    isDarkMode: Boolean = false,

    onUserProfilePictureClick: (() -> Unit)? = null,
    viewModel: SingleChatViewModel,
    isOnline : IsOnlineStatus = IsOnlineStatus.ISTYPING
) {


    val chatListItem by viewModel.chatListItem.collectAsState(initial = null)


    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Right,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(if (isDarkMode) kilidDarkBackgound else kilidWhiteBackgound)
            ) {

                Icon(
                    painter  = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { onBackPressed() }
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(23.dp))
                FastImage(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape).
                    clickable {
                              onUserProfilePictureClick?.invoke()
                    }
                    ,
                    imageUrl = chatListItem?.smallProfileImage, // Consider using actual image URL
                    contentDescription = "Plp Item Image",
                    isRoundImage = true
                )
                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 11.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = chatListItem?.userNickName?:"",
                        style = KilidTypography.h5,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                   when(isOnline){
                       IsOnlineStatus.OFFLINE -> {
                            null
                       }
                       IsOnlineStatus.ONLINE -> {

                           Text(text = "online",
                               style = KilidTypography.h3,
                               color = OnlineTextColor)

                       }
                       IsOnlineStatus.LASTSEENRECENTLY -> {


                           Text(text = "last seen recently",
                               style = KilidTypography.h3,
                               color = OnlineTextColor)
                       }
                       IsOnlineStatus.WAITINGFORNETWORK -> {

                           Text(text = "waiting for network",
                               style = KilidTypography.h3,
                               color = OnlineTextColor)
                       }

                       IsOnlineStatus.ISTYPING -> {
                           Text(text = "...typing",
                               style = KilidTypography.h3,
                               color = OnlineTextColor)

                       }

                       IsOnlineStatus.ICONNECTING -> {

                           Text(text = "...connecting",
                               style = KilidTypography.h3,
                               color = OnlineTextColor)
                       }
                   }

                }
                Icon(
                    painter = painterResource(id = R.drawable.menu ),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { onBackPressed() }
                        .size(16.dp)
                )


            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        contentColor = Color.Black, // Changed to contrast with white background
        modifier = Modifier.border(BorderStroke(0.dp, Color.White))
    )
}
