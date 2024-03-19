package org.lotka.xenonx.domain.usecase.pdp


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PdpCountTrackerUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke(id: Int): Flow<ResultState<Boolean>> {
        return flowOf(repository.pdpCountTracker(id))
    }


}
