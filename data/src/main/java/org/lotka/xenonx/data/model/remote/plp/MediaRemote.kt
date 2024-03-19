package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class MediaRemote(
     var imageCount: Int?,
     var imageUrls: List<String?>?
)