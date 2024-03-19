package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep


@Keep

data class AttributesRemote(
     var age: Int?,
     var expired: Boolean?,
     var featured: Boolean?,
     var floorArea: Double?,
     var floorAreaUnit: String?,
     var hasVr: Boolean?,
     var landuseType: String?,
     var numOfBeds: Int?,
     var numOfParkings: Int?,
     var propertyType: String?,
     var showPrice: Boolean?,
     var specifics: List<SpecificRemote?>?,
     var verified: Boolean?,
     var vrLink: Any?
)