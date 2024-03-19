package org.lotka.xenonx.presentation.extension

fun String.isNumeric(): Boolean {
    return this.matches(Regex("\\d+"))
}