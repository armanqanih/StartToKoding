package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class ContentRemote(
     var attributes: AttributesRemote?,
     var contactInfo: ContactInfoRemote?,
     var identifier: String?,
     var location: LocationRemote?,
     var media: MediaRemote?,
     var organizationId: Int?,
     var pricing: PricingRemote?,
     var searchDate: String?,
     var title: String?,
     var type: String?
)