package org.lotka.xenonx.data.repository.home

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
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



    override suspend fun pushNewChatListModel(id: Int): ResultState<Boolean> {
        return dataSource.pushNewChatListModel(id)
    }

    override suspend fun getUserContactInfo(id: Int): ResultState<ContactInformation> {
        return dataSource.getContactInformation(id)
    }


    override  fun observeUserChatList(
        page: Int,
    ): Flow<ResultState<ChatListResponseModel>> {
        return dataSource.observeUserChatList(page)
    }


    override suspend fun getAppUpdate(): ResultState<AppStatusResponse> {
        return dataSource.getAppStatus()
    }


}