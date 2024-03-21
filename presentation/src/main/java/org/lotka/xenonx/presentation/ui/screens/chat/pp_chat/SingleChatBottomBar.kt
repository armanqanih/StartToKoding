package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.ChatListViewModel

@Composable
fun SingleChatBottomBar(
   viewModel: ChatListViewModel
) {
    var messageText by remember { mutableStateOf("") }

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Absolute.Left,
        verticalAlignment = Alignment.CenterVertically

        ){
        Icon(
            painter  = painterResource(id = R.drawable.emogi_icon),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 13.dp)
                .clickable { }
                .size(27.dp)
        )

        Spacer(   modifier = Modifier.width(4.dp))

//        in there we need TextField not text I use just for Test
         TextField(
             modifier = Modifier.width(230.dp)

             ,   value = messageText,
             onValueChange = { messageText = it
             },

             label = {
                 Box (modifier = Modifier.fillMaxWidth()
                 , contentAlignment = Alignment.CenterEnd
                 ){
                 Text(text = "Message ", fontSize = 18.sp,
                     )

                 }
             },
             leadingIcon = {
                 Icon(
                     Icons.Filled.Send,
                     contentDescription = "Back",
                     modifier = Modifier
                         .clickable {
                             if (messageText.isNotBlank()) {
                                 viewModel.sendMessage(messageText)
                                 messageText = ""
                             }
                         }
                         .size(27.dp)
                 )
             }
             )



        Spacer(   modifier = Modifier.weight(3f))
        Icon(
            painter  = painterResource(id = R.drawable.choose_image),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { }
                .size(27.dp)
        )
        Spacer(   modifier = Modifier.width(18.dp))
        Icon(
            painter  = painterResource(id = R.drawable.voice_send),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { }
                .size(27.dp)
        )






    }

}