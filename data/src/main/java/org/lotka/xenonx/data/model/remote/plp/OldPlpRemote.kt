package org.lotka.xenonx.data.model.remote.plp


import androidx.annotation.Keep

import org.lotka.xenonx.domain.enums.LandUseTypes
import org.lotka.xenonx.domain.enums.ListingType
import org.lotka.xenonx.domain.model.model.plp.PlpItemResultModel
import org.lotka.xenonx.domain.model.model.plp.PlpResponseModel

@Keep

data class OldPlpRemote(
     var content: List<ContentRemote?>?,
     var totalListings: Int?
) : org.lotka.xenonx.data.base.ResponseObject<PlpResponseModel> {
    override fun toDomain(): PlpResponseModel {
        try {
            return PlpResponseModel(
                plpItemResultModel = content?.map { res ->

                    val listingType: ListingType =
                        ListingType.getByNameEn(res?.attributes?.listingType.toString())
                            ?: ListingType.SALE_AND_PRE_SALE
                    val landUseTypes: LandUseTypes =
                        LandUseTypes.getLandUseTypeByNameFa(res?.attributes?.landuseType.toString())
                            ?: LandUseTypes.RESIDENTIAL

                    PlpItemResultModel(
                        userFirstName = res?.contactInfo?.name,
                        lastMessageText = res?.contactInfo?.logoUrl,
                        isPremiumUser = res?.attributes?.featured,
                        id = res?.identifier?.toInt() ?: 0,
                        lastTypingDate = res?.searchDate?.toString() ?: "",
                        numUnreadMessage = res?.attributes?.numOfBeds,
                        userLastName = res?.title,
                        smallProfileImage = res?.media?.imageUrls?.firstOrNull(),
                        lastMessageDate = res?.searchDate?.toString() ?: "",
                        lastChatSeenDate = res?.searchDate?.toLong() ?: 0,
                        lastMessageStatus = res?.attributes?.listingType,
                        lastMessageType = res?.attributes?.landuseType,
                        hasStory = res?.attributes?.featured,


                        )
                },
                total = totalListings ?: 1000,
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return PlpResponseModel(
            plpItemResultModel = emptyList(),
            total = 0
        )
    }

}