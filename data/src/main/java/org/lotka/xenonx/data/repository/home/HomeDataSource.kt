package org.lotka.xenonx.data.repository.home


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.util.ResultState

interface HomeDataSource {

    suspend fun searchLocation(text: String): ResultState<LocationSearchModel>

    suspend fun pdpDetail(id: Int): ResultState<PdpModel>
    suspend fun pushNewChatListModel(id: Int): ResultState<Boolean>

     fun observeUserChatList(page: Int): Flow<ResultState<ChatListResponseModel>>
    suspend fun getContactInformation(identifier: Int): ResultState<ContactInformation>
    suspend fun getAppStatus(): ResultState<AppStatusResponse>
}