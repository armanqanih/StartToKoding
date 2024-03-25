package org.lotka.xenonx.domain.usecase.chat


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ObserveUserChatListingUseCase @Inject
constructor(private val repository: HomeRepository) {


    operator fun invoke(page: Int): Flow<ResultState<ChatListResponseModel>> {

        return repository.observeUserChatList(page = page)

    }


}
