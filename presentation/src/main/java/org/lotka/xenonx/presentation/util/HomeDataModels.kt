package org.lotka.xenonx.presentation.util


data class HomeType(

    val listingType: String?,

    val landUseTypes: String?,

    val homeUseTypes: String?
)


data class HomeProperty(

    val meterage: String?,

    val pricePerMeter: String?,

    val totalPrice: String?,

    val deposit: String?,

    val rent: String?,

    val homeAge: String?,

    val numOfBedroom: String?,

    val numOfParking: String?,

    val agreedPrice: Boolean?,

    val fullyMortgage: Boolean?,

    val newBuilt: Boolean?
)


data class HomeCondition(

    val loanMount: String?,

    val unitShareMount: String?,

    val checkedConditions: String?,

    val checkedFacilities: String?,
)


data class HomeAddress(

    val cityId: String?,

    val cityName: String?,

    val exactNameLocal: String?,

    val phraseToShow: String?,

    val type: String?,

    val locationId: String?,

    val mainRoad: String?,

    val lat: String?,

    val long: String?
)


data class HomeDescription(

    val title: String?,

    val description: String?,

    val agentId: String?,

    val isAdmin: Boolean,

    val agentFirstName: String?,

    val agentLastName: String?,
)

