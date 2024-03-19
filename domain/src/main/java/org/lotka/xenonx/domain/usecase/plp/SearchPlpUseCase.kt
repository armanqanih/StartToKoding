package org.lotka.xenonx.domain.usecase.plp


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.lotka.xenonx.domain.model.model.plp.PlpResponseModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchPlpUseCase @Inject constructor(private val repository: HomeRepository) {


    suspend operator fun invoke(
        page: Int
    ): Flow<ResultState<PlpResponseModel>> {

        return flowOf(
            repository.loadPlpList(
                page = page
            )
        )
    }


}
