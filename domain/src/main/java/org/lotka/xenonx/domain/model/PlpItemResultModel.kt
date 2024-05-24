package org.lotka.xenonx.domain.model

import com.google.errorprone.annotations.Keep

@Keep
data class PlpItemResultModel(
    var agencyName : String?,
    var agencyLogoUrl : String?,
    var featured: Boolean?,
    var floorArea: Int?,
    var id: Int,
    var numBeds: Int?,
    var numParkings: Int?,
    var priceOrDeposit: Long?,
    var title: String?,
    var unitPriceOrRent: Long?,
    var agreedPrice: Boolean?,
    var coverPicture: String?,
    var searchDate : String?,
    var locationPhrase : String?,

    )