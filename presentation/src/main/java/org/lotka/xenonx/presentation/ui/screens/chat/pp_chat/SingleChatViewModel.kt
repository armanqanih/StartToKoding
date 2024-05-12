package org.lotka.xenonx.presentation.ui.screens.chat.pp_chat


import android.os.Message
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseItemModel
import javax.inject.Inject

@HiltViewModel
class SingleChatViewModel @Inject constructor(

):ViewModel() {


    val chatListItem = MutableStateFlow<ChatListResponseItemModel?>(null)

    private val _messages: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    // Exposed immutable state for observing
    val messages = _messages.asStateFlow()

    // Function to send a message
    fun sendMessage(message: String) {
        // Append the new message to the existing list of messages
        _messages.value += message
    }
    fun getMessageText(index: Int): String? {
        return messages.value.getOrNull(index)
    }




    fun editMessage(originalMessage: String, editedMessage: String) {
        val updatedMessages = _messages.value.toMutableList().apply {
            val index = indexOf(originalMessage)
            if (index != -1) {
                set(index, editedMessage)
            }
        }
        _messages.value = updatedMessages
    }
    // Function to edit a message
//    fun editMessage(originalMessage: String, editedMessage: String) {
//        val updatedMessages = _messages.value.toMutableList().apply {
//            val index = indexOf(originalMessage)
//            if (index != -1) {
//                set(index, editedMessage)
//            }
//        }
//        _messages.value = updatedMessages
//    }

    // Function to delete a message
    fun deleteMessage(message: String) {
        val updatedMessages = _messages.value.toMutableList()
        updatedMessages.remove(message)
        _messages.value = updatedMessages
    }

    // Function to reply to a message
    fun replyToMessage(oldMessage: String, newMessage: String) {
        val repliedMessage = "$newMessage\n $oldMessage"
        val updatedMessages = _messages.value.toMutableList().apply {
            add(repliedMessage)
        }
        _messages.value = updatedMessages
    }
}

