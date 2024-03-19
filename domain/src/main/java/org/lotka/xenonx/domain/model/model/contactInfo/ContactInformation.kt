package org.lotka.xenonx.domain.model.model.contactInfo


import androidx.annotation.Keep

@Keep
data class ContactInformation(
    var callNumber: String?,
    var departmentId: Int?,
    var departmentLogoUrl: String?,
    var departmentName: String?,
    var fullName: String?,
    var type: String?,
    var whatsappAvailibility: Boolean?
)