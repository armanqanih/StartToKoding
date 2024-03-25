package org.lotka.xenonx.data.model.remote.chat_list

import org.lotka.xenonx.domain.enums.UserVerificationStatus
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseItemModel
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.util.convertMillisToLocalizedDateTime

data class ChatListResponseRemote(
    var chatListItemModel: List<ChatListResponseItemRemote?>?,
    var totalChatItems: Int?
)


 fun ChatListResponseRemote.toDomain(): ChatListResponseModel {
    return ChatListResponseModel(
        chatListItemModel = chatListItemModel?.map { it?.toDomain() },
        total = totalChatItems
    )

}

private fun ChatListResponseItemRemote?.toDomain() : ChatListResponseItemModel {
    // Create a Calendar instance and set it to the current time in UTC
    // Create a Calendar instance and set it to the current time in UTC

    return ChatListResponseItemModel(
        chatId = this?.chatId,
        userId = this?.userId,
        userNickName = this?.userNickName,
        smallProfileImage = this?.smallProfileImage,
        lastMessageText = this?.lastMessageText,
        isGood = if(this?.userNickName=="arman") true else false,
        numUnreadMessage = this?.numUnreadMessage,
        lastOnlineTime = convertMillisToLocalizedDateTime(this?.lastOnlineTime,"Asia/Tehran"),
        lastTypingTime = convertMillisToLocalizedDateTime(this?.lastTypingTime,"Asia/Tehran"),
        lastMessageTime = convertMillisToLocalizedDateTime(this?.lastMessageTime,"Asia/Tehran"),
        lastChatSeenTime = convertMillisToLocalizedDateTime(this?.lastChatSeenTime,"Asia/Tehran"),
        lastMessageStatus = this?.lastMessageStatus,
        lastMessageType = this?.lastMessageType,
        verificationStatus = this?.verificationStatus.let {
            when(it) {
                "BLUE" -> UserVerificationStatus.BLUE_VERIFIED
                "ADMIN" -> UserVerificationStatus.ADMIN_VERIFIED
                "GREEN" -> UserVerificationStatus.GREEN_VERIFIED
                "NONE" -> UserVerificationStatus.NONE
                else -> UserVerificationStatus.NONE
            }

        },
        isSilent = this?.isSilent,
        isLockAccount = this?.isLockAccount,
        isChatPinned = this?.isChatPinned,
        isPremiumUser = this?.isPremiumUser
    )
}
