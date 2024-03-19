package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class LocationRemote(
     var latitude: Double?,
     var locationName: String?,
     var longitude: Double?
)