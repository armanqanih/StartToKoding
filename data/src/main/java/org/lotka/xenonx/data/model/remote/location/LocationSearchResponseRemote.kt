package org.lotka.xenonx.data.model.remote.location


import androidx.annotation.Keep

import org.lotka.xenonx.domain.model.model.location.LocationSearchModel

@Keep

data class LocationSearchResponseRemote(
     var results: List<LocationSearchItemRemote?>?,
     var size: Int?
) : org.lotka.xenonx.data.base.ResponseObject<LocationSearchModel> {
    override fun toDomain(): LocationSearchModel {
        return LocationSearchModel(
            results = results?.map { it?.toDomain() },
            size = size
        )
    }
}