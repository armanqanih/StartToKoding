package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep


@Keep

data class BreadcrumbLinkRemote(
     var index: Int?,
     var linkUrl: String?,
     var name: String?
)