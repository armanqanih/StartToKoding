package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep


@Keep

data class PricingRemote(
     var agreedPrice: Boolean?,
     var currency: String?,
     var deposit: Long?,
     var depositOnly: Boolean?,
     var price: Long?,
     var rent: Long?,
     var unitPrice: Long?
)