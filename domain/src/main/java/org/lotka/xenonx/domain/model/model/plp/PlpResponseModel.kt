package org.lotka.xenonx.domain.model.model.plp


import androidx.annotation.Keep

@Keep
data class PlpResponseModel(
    var plpItemResultModel: List<PlpItemResultModel>?, var total: Int?
)