package com.example.chatwithme.domain.model

import org.lotka.xenonx.domain.model.ChatMessage

data class MessageRegister(
    var chatMessage: ChatMessage,
    var isMessageFromOpponent: Boolean
)