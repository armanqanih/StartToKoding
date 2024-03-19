package org.lotka.xenonx.domain.model.model.plp


import androidx.annotation.Keep
import org.lotka.xenonx.domain.enums.IsItemPinnedStatus
import org.lotka.xenonx.domain.enums.IsOnlineStatus
import org.lotka.xenonx.domain.enums.UserVerificationStatus

@Keep
data class PlpItemResultModel(
    var id: Int,
    var userFirstName: String?,
    var userLastName: String?,
    var smallProfileImage: String?,
    var lastMessageText: String?,
    var lastMessageDate: String?,
    var numUnreadMessage: Int?,
    var isOnline: IsOnlineStatus = IsOnlineStatus.OFFLINE,
    var lastTypingDate: String?,
    var isPremiumUser: Boolean?,
    var lastMessageType: String?,
    var lastChatSeenDate: Long?,
    var lastMessageStatus: String?,
    var hasStory: Boolean?,
    var isItemPinned: IsItemPinnedStatus = IsItemPinnedStatus.NONE,
    var verificationStatus: UserVerificationStatus = UserVerificationStatus.NONE,
    var isSilent: Boolean = false,
    var isTyping: Boolean = false,
    var isLockAccount: Boolean = false,
    var isSentAPicture: Boolean = false,
    var isUnreadMessage: Boolean = false

)