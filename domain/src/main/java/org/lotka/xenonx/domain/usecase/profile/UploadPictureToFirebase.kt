package org.lotka.xenonx.domain.usecase.profile

import android.net.Uri
import org.lotka.xenonx.domain.repository.ProfileScreenRepository


class UploadPictureToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(url: Uri) = profileScreenRepository.uploadPictureToFirebase(url)

}