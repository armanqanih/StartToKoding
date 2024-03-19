package org.lotka.xenonx.domain.model.model.location


import androidx.annotation.Keep


@Keep
data class LocationSearchItem(
    var cityId: Long?,
    var cityName: String?,
    var exactNameLat: String?,
    var exactNameLocal: String?,
    var geographicType: String?,
    var globalLocationId: Long?,
    var id: Long?,
    var locationId: Long?,
    var locationTypeId: Long?,
    var phraseToShow: String?,
    var score: Double?,
    var type: String?,
    var useForLegacyService: Boolean?,
    var isSelected: Boolean = false
)

