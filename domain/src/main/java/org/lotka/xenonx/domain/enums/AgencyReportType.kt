package org.lotka.xenonx.domain.enums

import androidx.annotation.StringRes
import org.lotka.xenonx.domain.R


sealed class AgencyReportType(
    val index: Int,
    @StringRes val resourceId: Int,
    val category: String
) {
    object AdvertisementType :
        org.lotka.xenonx.domain.enums.AgencyReportType(
            index = 0,
            R.string.advertisement_type,
            "perListingType"
        )

    object UserType : org.lotka.xenonx.domain.enums.AgencyReportType(
        index = 1,
        R.string.user_type,
        "perLanduseType"
    )

    object AdvertisementStatus :
        org.lotka.xenonx.domain.enums.AgencyReportType(
            index = 2,
            R.string.advertisement_status,
            "perState"
        )

    object AdvertisementWithPhoto :
        org.lotka.xenonx.domain.enums.AgencyReportType(
            index = 3,
            R.string.advertisement_with_photo,
            "perHavePic"
        )

    object RegisteredBy : org.lotka.xenonx.domain.enums.AgencyReportType(
        index = 4,
        R.string.submitted_by,
        "perSource"
    )

    object PersonalAdvertisement :
        org.lotka.xenonx.domain.enums.AgencyReportType(
            index = 5,
            R.string.personal_advertisement,
            "OwnerListing"
        )

    object OccasionAdvertisement :
        org.lotka.xenonx.domain.enums.AgencyReportType(
            index = 6,
            R.string.occasion_advertisement,
            "Premium"
        )
}