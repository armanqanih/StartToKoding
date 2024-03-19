package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class AttributesRemote(
     var expired: Boolean?,
     var featured: Boolean?,
     var floorArea: Double?,
     var floorAreaUnit: String?,
     var hasVr: Boolean?,
     var landuseType: String?,
     var listingType: String?,
     var numOfBeds: Int?,
     var numberOfParking: Int?,
     var showPrice: Boolean?,
     var verified: Boolean?
)