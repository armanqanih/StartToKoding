package org.lotka.xenonx.presentation.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DispatchersProviderImpl @Inject constructor() {

    val dispatchersProvider = object : DispatchersProvider {
        override fun getIO(): CoroutineDispatcher {
            return Dispatchers.IO
        }

        override fun getMain(): CoroutineDispatcher {
            return Dispatchers.Main
        }

        override fun getDefault(): CoroutineDispatcher {
            return Dispatchers.Default
        }

    }

}