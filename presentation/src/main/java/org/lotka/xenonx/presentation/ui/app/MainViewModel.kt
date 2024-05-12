package org.lotka.xenonx.presentation.ui.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.update.GetUpdateUseCase
import org.lotka.xenonx.domain.util.ResultState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appUpdateUseCase: GetUpdateUseCase,
) : ViewModel() {

    init {
        checkCdnForUpdate()
    }

    private fun checkCdnForUpdate() {
        viewModelScope.launch {
            appUpdateUseCase.invoke().collect {
                when (it) {
                    is ResultState.Error -> {
                        Timber.tag("updateLogger").d("error: ${it.error.error}")
                        //could not connect to cdn or there is no internet connection
                    }

                    is ResultState.Loading -> {}
                    is ResultState.Success -> {
                        Timber.tag("updateLogger").d("Success: ${it.data.toString()}")
//                        customUpdateManager.setAppVersionData(
//                            updateFunctionality = it.data?.versions?.updateFunctionality ?: false,
//                            autoUpdateEnabled = it.data?.versions?.autoUpdateEnabled ?: true,
//                            indicatedUpdate = it.data?.versions?.indicatedUpdate ?: -1,
//                            optionalUpdate = it.data?.versions?.optionalUpdate ?: -1,
//                            forceUpdate = it.data?.versions?.minimum_available_version ?: -1,
//                            downloadLink = it.data?.versions?.downloadLink ?: "",
//                            updateFeatures = it.data?.versions?.updateFeatures ?: emptyList(),
//                        )


                    }

                    else -> {}
                }
            }
        }

    }
}