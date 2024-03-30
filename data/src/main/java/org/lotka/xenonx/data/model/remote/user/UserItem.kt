package org.lotka.xenonx.data.model.remote.user



data class UserItemRemote(
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
data class UserListItemRemote(
    var userListItemRemote: List<UserItemRemote?>?, var total: Int?
)

private fun UserItemRemote?.toDomain() : UserItemRemote {


    return UserItemRemote(
        profileUUID = this?.profileUUID ?: "",
        userEmail = this?.userEmail ?: "",
        oneSignalUserId = this?.oneSignalUserId ?: "",
        userName = this?.userName ?: "",
        userProfilePictureUrl = this?.userProfilePictureUrl ?: "",
        userSurName = this?.userSurName ?: "",
        userBio = this?.userBio ?: "",
        userPhoneNumber = this?.userPhoneNumber ?: "",
        status = this?.status ?: ""

    )

}