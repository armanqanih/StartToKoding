package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.util.ResultState


interface HomeRepository {
    suspend fun searchLocation(text: String): ResultState<LocationSearchModel>



    suspend fun pushNewChatListModel(id: Int): ResultState<Boolean>

    suspend fun getUserContactInfo(id: Int): ResultState<ContactInformation>

     fun observeUserChatList(page: Int): Flow<ResultState<ChatListResponseModel>>
    suspend fun getAppUpdate(): ResultState<AppStatusResponse>


}
