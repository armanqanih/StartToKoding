package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep


@Keep

data class PricingRemote(
     var agreedPrice: Boolean?,
     var currency: String?,
     var deposit: Long?,
     var depositOnly: Boolean?,
     var price: Long?,
     var rent: Long?,
     var rentFrequency: Any?,
     var unitPrice: Long?
)