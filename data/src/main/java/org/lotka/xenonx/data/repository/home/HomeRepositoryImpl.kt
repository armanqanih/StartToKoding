package org.lotka.xenonx.data.repository.home

import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.plp.PlpResponseModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(private val dataSource: HomeDataSource) :
    HomeRepository {
    override suspend fun searchLocation(text: String): ResultState<LocationSearchModel> {
        return dataSource.searchLocation(text)
    }

    override suspend fun loadPdp(id: Int): ResultState<PdpModel> {
        return dataSource.pdpDetail(id)
    }

    override suspend fun pdpCountTracker(id: Int): ResultState<Boolean> {
        return dataSource.pdpCountTracker(id)
    }

    override suspend fun getUserContactInfo(id: Int): ResultState<ContactInformation> {
        return dataSource.getContactInformation(id)
    }


    override suspend fun loadPlpList(
        page: Int,
    ): ResultState<PlpResponseModel> {
        return dataSource.loadPlpList(page)
    }


    override suspend fun getAppUpdate(): ResultState<AppStatusResponse> {
        return dataSource.getAppStatus()
    }


}