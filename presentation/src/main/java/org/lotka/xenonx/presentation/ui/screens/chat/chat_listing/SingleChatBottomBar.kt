package org.lotka.xenonx.presentation.ui.screens.chat.chat_listing
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.TelegramTextField
import org.lotka.xenonx.presentation.theme.TelegrampinkColor

@Composable
fun ChatListBottomBar(
    viewModel: ChatListViewModel) {


    Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .width(279.dp).height(48.dp)




    ) {
        Icon(
            painter = painterResource(id = R.drawable.chat_icon),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 13.dp)
                .clickable { }
                .size(27.dp)
        )


        Spacer(modifier = Modifier.width(12.dp))
        Icon(
            painter = painterResource(id = R.drawable.search_icon),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { }
                .size(27.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Box(modifier = Modifier
            .size(width = 103.dp, height = 40.dp)
            .background(TelegrampinkColor)
            .clip(shape = RoundedCornerShape(100.dp))
        
        ){
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { }
                    .size(27.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(text = "setting")
            
            
        }
        
        Spacer(modifier = Modifier.width(18.dp))
  


    }

}