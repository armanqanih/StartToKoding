package org.lotka.xenonx.data.model.remote.contactInfo


import androidx.annotation.Keep

import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation

@Keep
data class ContactInformationRemote(
     var callNumber: String?,
     var departmentId: Int?,
     var departmentLogoUrl: String?,
     var departmentName: String?,
     var fullName: String?,
     var type: String?,
     var whatsappAvailibility: Boolean?
) : org.lotka.xenonx.data.base.ResponseObject<ContactInformation> {
    override fun toDomain(): ContactInformation {
        return ContactInformation(
            callNumber = callNumber,
            departmentId = departmentId,
            departmentLogoUrl = departmentLogoUrl,
            departmentName = departmentName,
            fullName = fullName,
            type = type,
            whatsappAvailibility = whatsappAvailibility
        )
    }


}