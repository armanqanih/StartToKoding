package org.lotka.xenonx.presentation.composables.compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

fun <T> mutableStateOfWithObserver(
    initialValue: T,
    onChange: (T) -> Unit
): MutableState<T> {
    var state = mutableStateOf(initialValue)
    return object : MutableState<T> by state {
        override var value: T
            get() = state.value
            set(value) {
                if (value != state.value) {
                    onChange(value)
                }
                state.value = value
            }
    }
}
