package org.lotka.xenonx.domain.model.model.chat.chat_list

data class ChatListResponseModel(
    var chatListItemModel: List<ChatListResponseItemModel?>?, var total: Int?
)