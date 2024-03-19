package org.lotka.xenonx.domain.usecase.location


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchLocationUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke(text: String): Flow<ResultState<LocationSearchModel>> {
        return flowOf(repository.searchLocation(text))
    }


}
