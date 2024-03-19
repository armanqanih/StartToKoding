package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep


@Keep

data class ContactInfoRemote(
     var logoUrl: String?,
     var name: String?,
     var agentId: Long?,
     var agentName: String?,
)