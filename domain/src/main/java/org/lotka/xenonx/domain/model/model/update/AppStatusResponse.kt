package org.lotka.xenonx.domain.model.model.update


import androidx.annotation.Keep

@Keep
data class AppStatusResponse(
    var versions: Versions?
)