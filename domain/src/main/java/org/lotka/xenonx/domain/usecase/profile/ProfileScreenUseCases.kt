package org.lotka.xenonx.domain.usecase.profile

data class ProfileScreenUseCases(
    val createOrUpdateProfileToFirebase: CreateOrUpdateProfileToFirebase,
    val loadProfileFromFirebase: LoadProfileFromFirebase,
    val setUserStatusToFirebase: SetUserStatusToFirebase,
    val signOut: SignOut,
    val uploadPictureToFirebase: UploadPictureToFirebase
)