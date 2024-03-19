package org.lotka.xenonx.data.repository.home


import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.plp.PlpResponseModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.util.ResultState

interface HomeDataSource {

    suspend fun searchLocation(text: String): ResultState<LocationSearchModel>

    suspend fun pdpDetail(id: Int): ResultState<PdpModel>
    suspend fun pdpCountTracker(id: Int): ResultState<Boolean>

    suspend fun loadPlpList(page: Int): ResultState<PlpResponseModel>
    suspend fun getContactInformation(identifier: Int): ResultState<ContactInformation>
    suspend fun getAppStatus(): ResultState<AppStatusResponse>
}