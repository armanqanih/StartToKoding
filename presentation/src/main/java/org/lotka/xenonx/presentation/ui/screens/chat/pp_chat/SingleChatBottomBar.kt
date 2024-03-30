package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.composables.TelegramTextField

@Composable
fun SingleChatBottomBar(
    viewModel: SingleChatViewModel) {
    var messageText by remember { mutableStateOf("") }

    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
            .padding(horizontal = 16.dp),



    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.emogi_icon),
//            contentDescription = "Back",
//            modifier = Modifier
//                .padding(start = 13.dp)
//                .clickable { }
//                .size(27.dp)
//        )
//
//        Spacer(modifier = Modifier.width(4.dp))


        TelegramTextField(
            modifier = Modifier.fillMaxWidth().weight(3f)
                .align(Alignment.CenterVertically),
            textFieldValue = messageText,
            onValueChange = { messageText = it },
            placeholder = {
                Text(
                    text = "Message",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Left
                )

            }, leadingIcon =
            {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "send",
                    tint = Color.Gray, modifier = Modifier
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


//
//        Spacer(modifier = Modifier.weight(3f))
//        Icon(
//            painter = painterResource(id = R.drawable.choose_image),
//            contentDescription = "Back",
//            modifier = Modifier
//                .clickable { }
//                .size(27.dp)
//        )
//        Spacer(modifier = Modifier.width(18.dp))
//        Icon(
//            painter = painterResource(id = R.drawable.voice_send),
//            contentDescription = "Back",
//            modifier = Modifier
//                .clickable { }
//                .size(27.dp)
//        )


    }

}