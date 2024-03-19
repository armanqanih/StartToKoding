package org.lotka.xenonx.data.model.remote.location


import androidx.annotation.Keep

import org.lotka.xenonx.domain.model.model.location.LocationSearchItem

@Keep

data class LocationSearchItemRemote(
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
     var useForLegacyService: Boolean?
) : org.lotka.xenonx.data.base.ResponseObject<LocationSearchItem> {
    override fun toDomain(): LocationSearchItem {
        return LocationSearchItem(
            cityId = cityId,
            cityName = cityName,
            exactNameLat = exactNameLat,
            exactNameLocal = exactNameLocal,
            geographicType = geographicType,
            globalLocationId = globalLocationId,
            id = id,
            locationId = locationId,
            locationTypeId = locationTypeId,
            phraseToShow = phraseToShow,
            score = score,
            type = type,
            useForLegacyService = useForLegacyService
        )
    }
}