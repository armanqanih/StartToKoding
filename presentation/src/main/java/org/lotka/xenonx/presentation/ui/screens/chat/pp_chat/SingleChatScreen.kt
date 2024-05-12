package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.composables.TelegramTextField
import org.lotka.xenonx.presentation.theme.TelegramBackGround

@Composable
fun SingleChatScreen(
    onBackPressed: () -> Unit,
    onToggleTheme: () -> Unit,
    isDarkMode: Boolean = false,
    viewModel: SingleChatViewModel,
    navController: NavController,

    ) {
    val messages by viewModel.messages.collectAsState()
    val reversed = messages.reversed()





    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TelegramBackGround),
        topBar = {
            SingleChatTopBar(
                onUserProfilePictureClick = {},
                mainScreens = true,
                onToggleTheme = {
                    onToggleTheme()
                },
                onBackPressed = onBackPressed,
                isDarkMode = isDarkMode,
                viewModel = viewModel
            )
        },
        bottomBar = {
            SingleChatBottomBar(viewModel)
        },
        content = { PaddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(PaddingValues)
                    .fillMaxSize(),
                reverseLayout = true
            ) {
                items(messages.indices.toList()) { index ->
                    val message = viewModel.getMessageText(index)
                    message?.let { text ->
                        MessageItem(message = reversed[index] , viewModel = viewModel)
                    }
                }
            }
        }
    )
}

@Composable
fun MessageItem(
    message: String,
    viewModel: SingleChatViewModel,

    ) {
    var isMenuVisible by remember { mutableStateOf(false) }
    var editedMessage by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }



    Card(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(
                color = TelegramBackGround,
                shape = MaterialTheme.shapes.medium
            ),
        backgroundColor = TelegramBackGround,
        contentColor = TelegramBackGround,
        elevation = 1.dp
    ) {
        Column {


            if (isEditing) {
                // Text field for editing the message
                TelegramTextField(

                    textFieldValue = editedMessage,
                    onValueChange = { editedMessage = it },
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
                                    viewModel.editMessage(message, editedMessage)
                                    isEditing =
                                        false // Close editing mode after sending the edited message
                                    isMenuVisible = false // Close dropdown menu
                                }
                                .size(27.dp)
                        )
                    }

                )
            } else {

            }
            Text(
                text = message,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        isMenuVisible = true
                    }
//                    .longPressGestureFilter(onLongPress = { isMenuVisible = true })
            )

            if (isMenuVisible) {
                val menuItems = listOf("Edit", "Delete", "Reply")

                DropdownMenu(
                    expanded = isMenuVisible,
                    onDismissRequest = { isMenuVisible = false }
                ) {
                    menuItems.forEach { menuItem ->
                        DropdownMenuItem(onClick = {
                            when (menuItem) {
                                "Edit" -> {
//                                        viewModel.editMessage(message, editedMessage)
                                    isEditing = true
                                    editedMessage = message
                                }

                                "Delete" -> viewModel.deleteMessage(message)
                                "Reply" -> {

                                    viewModel.replyToMessage(message, "")

                                }
                            }
                            isMenuVisible = false
                        }) {
                            Text(menuItem)
                        }
                    }
                }
            }
        }
    }
}

