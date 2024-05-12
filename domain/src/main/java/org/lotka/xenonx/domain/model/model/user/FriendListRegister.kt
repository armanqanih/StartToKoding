package org.lotka.xenonx.domain.model.model.user

data class FriendListRegister(
    var chatRoomUUID: String = "",
    var registerUUID: String = "",
    var requesterEmail: String = "",
    var requesterUUID: String = "",
    var requesterOneSignalUserId: String = "",
    var acceptorEmail: String = "",
    var acceptorUUID: String = "",
    var acceptorOneSignalUserId: String = "",
    var status: String = "",
    var lastMessage: ChatMessage = ChatMessage()
)

data class ChatMessage(
    val profileUUID: String = "",
    var message: String = "",
    var date: Long = 0,
    var status: String = ""
)
