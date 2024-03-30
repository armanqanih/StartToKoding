package org.lotka.xenonx.domain.model.model.user



data class UserItem(
    var profileUUID: String = "",
    var userEmail: String = "",
    var oneSignalUserId: String = "",
    var userName: String = "",
    var userProfilePictureUrl: String = "",
    var userSurName: String = "",
    var userBio: String = "",
    var userPhoneNumber: String = "",
    var status: String = ""
)
data class UserListItem(
    var userListItem: List<UserItem?>?, var total: Int?
)