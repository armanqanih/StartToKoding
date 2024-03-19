package  org.lotka.xenonx.presentation.ui.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.presentation.util.DispatchersProvider
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val dispatchers: DispatchersProvider
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatchers.getMain() + SupervisorJob()

    private val _loadingState = MutableStateFlow<Boolean>(false)
    val loadingState: Flow<Boolean> = _loadingState

    fun execute(job: suspend () -> Unit) = launch {
        withContext(dispatchers.getIO()) { job.invoke() }
    }

    fun <T> getResult(resultState: ResultState<T>) {
        execute {
            _loadingState.value = true
            resultState
            _loadingState.value = false
        }
    }
}
