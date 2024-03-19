package org.lotka.xenonx.domain.model.model.location


import androidx.annotation.Keep

@Keep

data class LocationSearchModel(
    var results: List<LocationSearchItem?>?,
    var size: Int?
)