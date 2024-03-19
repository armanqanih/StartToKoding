package org.lotka.xenonx.domain.usecase.update


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.repository.HomeRepository
import org.lotka.xenonx.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetUpdateUseCase @Inject constructor(private val repository: HomeRepository) {

    suspend operator fun invoke(): Flow<ResultState<AppStatusResponse>> {
        return flowOf(repository.getAppUpdate())
    }


}
