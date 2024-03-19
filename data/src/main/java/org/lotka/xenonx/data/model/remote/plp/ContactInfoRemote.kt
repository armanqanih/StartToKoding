package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class ContactInfoRemote(
     var agentId: Int?,
     var logoUrl: String?,
     var name: String?,
     var type: String?
)