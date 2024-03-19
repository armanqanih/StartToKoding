package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep


@Keep

data class MediaRemote(
     var isCover: Boolean?,
     var mediaUrl: Any?,
     var photoLargeUrl: String?,
     var photoMediumUrl: Any?,
     var photoSmallUrl: String?,
     var type: String?
)