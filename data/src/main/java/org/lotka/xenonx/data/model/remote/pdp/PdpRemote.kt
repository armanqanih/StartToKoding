package org.lotka.xenonx.data.model.remote.pdp


import androidx.annotation.Keep

import org.lotka.xenonx.domain.enums.HomeFeaturesEnum
import org.lotka.xenonx.domain.enums.LandUseTypes
import org.lotka.xenonx.domain.enums.ListingType
import org.lotka.xenonx.domain.enums.getAvailableFeatures
import org.lotka.xenonx.domain.model.model.pdp.PdpModel

@Keep

data class PdpRemote(
     var attributes: AttributesRemote?,
     var breadcrumbLinks: List<BreadcrumbLinkRemote?>?,
     var description: String?,
     var httpStatus: Any?,
     var identifier: String?,
     var isApproximate: Boolean?,
     var listingType: String?,
     var location: LocationRemote?,
     var media: List<MediaRemote?>?,
     var pricing: PricingRemote?,
     var searchDate: String?,
     var seoDescription: String?,
     var status: Any?,
     var title: String?,
     var type: String?,
     var contactInfo: ContactInfoRemote?
) : org.lotka.xenonx.data.base.ResponseObject<PdpModel> {
    override fun toDomain(): PdpModel {

        //find listing type
        val listingType = when (listingType) {
            "buy" -> {
                ListingType.SALE_AND_PRE_SALE
            }

            "rent" -> {
                ListingType.MORTGAGE_AND_RENT
            }

            else -> {
                //if cant find set to sale/presale
                ListingType.SALE_AND_PRE_SALE
            }
        }


        //get homeFeatures
        val homeFeatures: MutableList<HomeFeaturesEnum>? = attributes?.specifics?.map { it?.type }
            ?.let { getAvailableFeatures(it).toMutableList() }
        val isElevatorAvailable: Boolean = homeFeatures?.contains(HomeFeaturesEnum.ELEVATOR) == true
        val isStorageAvailable: Boolean = homeFeatures?.contains(HomeFeaturesEnum.STORAGE) == true
        //remove elevator from list
        homeFeatures?.remove(HomeFeaturesEnum.ELEVATOR)
        homeFeatures?.remove(HomeFeaturesEnum.STORAGE)


        return PdpModel(
            id = identifier?.toInt() ?: -1,

            listingType = listingType,
            landUseTypes = if (attributes?.landuseType == "مسکونی") LandUseTypes.RESIDENTIAL else LandUseTypes.COMMERCIAL,


            featured = false,

            largePictures = media?.map { it?.photoLargeUrl ?: "" } ?: emptyList(),
            smallPictures = media?.map { it?.photoSmallUrl ?: "" } ?: emptyList(),


            priceOrMortgage = if (listingType == ListingType.SALE_AND_PRE_SALE) pricing?.price
                ?: -1L else pricing?.deposit ?: -1L,
            unitPriceOrRent = if (listingType == ListingType.SALE_AND_PRE_SALE) pricing?.unitPrice
                ?: -1L else pricing?.rent ?: -1L,
            agreedPrice = pricing?.agreedPrice ?: false,


            floorArea = attributes?.floorArea?.toInt() ?: 0,
            age = attributes?.age ?: 0,

            description = description ?: "",
            title = title ?: "",


            numberOfParking = attributes?.numOfParkings,
            numberOfRooms = attributes?.numOfBeds,
            isElevatorAvailable = isElevatorAvailable,
            isStorageAvailable = isStorageAvailable,
            homeFeatures = homeFeatures,
            locationPhrase = location?.locationName ?: "-",
            mobileNumber = "09351699889",
            agencyLogo = contactInfo?.logoUrl,
            agentName = contactInfo?.name,
            fullyMortgage = pricing?.depositOnly


        )
    }
}