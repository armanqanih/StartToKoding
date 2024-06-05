package org.lotka.xenonx.data.model

import com.google.gson.annotations.SerializedName


import org.lotka.xenonx.domain.model.CoinModel

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): CoinModel {
    return CoinModel(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}