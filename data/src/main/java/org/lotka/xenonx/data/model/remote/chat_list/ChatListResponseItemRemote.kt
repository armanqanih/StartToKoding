package org.lotka.xenonx.data.model.remote.chat_list

import androidx.annotation.Keep

@Keep
data class ChatListResponseItemRemote(
    var chatId: String? = null,
    var userId: String? = null,
    var userNickName: String? = null,
    var smallProfileImage: String? = null,
    var lastMessageText: String? = null,
    var numUnreadMessage: Long? = null,

    var lastMessageType: String? = null,
    var lastMessageStatus: String? = null,
    var verificationStatus: String? = null,
    var isSilent: Boolean? = null,
    var isLockAccount: Boolean? = null,
    var isChatPinned: Boolean? = null,

    var isPremiumUser: Boolean? = null,
    var lastOnlineTime: Long? = null,
    var lastTypingTime: Long? = null,
    var lastChatSeenTime: Long? = null,
    var lastMessageTime: Long? = null,
)