package org.lotka.xenonx.domain.model.model.chat.chat_list

import androidx.annotation.Keep
import org.lotka.xenonx.domain.enums.UserVerificationStatus

@Keep
data class ChatListResponseItemModel(
    var chatId: String? = null,
    var userId: String? = null,
    var userNickName: String? = null,
    var smallProfileImage: String? = null,
    var lastMessageText: String? = null,
    var numUnreadMessage: Long? = null,

    var isGood: Boolean? = null,
    var lastOnlineTime: String? = null,
    var lastTypingTime: String? = null,
    var lastChatSeenTime: String? = null,
    var lastMessageTime: String? = null,

    var lastMessageType: String? = null,
    var lastMessageStatus: String? = null,
    var verificationStatus: UserVerificationStatus? = null,
    var isSilent: Boolean? = null,
    var isLockAccount: Boolean? = null,
    var isChatPinned: Boolean? = null,
    var isPremiumUser: Boolean? = null,
)