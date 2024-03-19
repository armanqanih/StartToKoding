package org.lotka.xenonx.domain.model.model.pdp


import androidx.annotation.Keep
import org.lotka.xenonx.domain.enums.HomeFeaturesEnum
import org.lotka.xenonx.domain.enums.LandUseTypes
import org.lotka.xenonx.domain.enums.ListingType

@Keep

data class PdpModel(
    var title: String,
    var id: Int,
    var featured: Boolean = false,
    var largePictures: List<String> = listOf(),
    var smallPictures: List<String> = listOf(),
    var listingType: ListingType,
    var agreedPrice: Boolean?,
    var fullyMortgage: Boolean?,
    var priceOrMortgage: Long,
    var unitPriceOrRent: Long,
    var floorArea: Int = 0,
    var age: Int = 0,
    var numberOfRooms: Int? = null,
    var numberOfParking: Int? = null,
    var isStorageAvailable: Boolean = false,
    val isElevatorAvailable: Boolean = false,
    var description: String?,
    var landUseTypes: LandUseTypes,
    var homeFeatures: List<HomeFeaturesEnum?>? = emptyList(),
    var locationPhrase: String = "",
    val mobileNumber: String = "09351699889",
    val agentOrAgencyImage: String = "",
    val agentName: String? = null,
    val agencyLogo: String? = null


)