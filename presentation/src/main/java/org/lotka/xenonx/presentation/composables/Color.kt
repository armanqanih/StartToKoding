package org.lotka.xenonx.presentation.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import org.lotka.xenonx.presentation.theme.kilidDarkObjects
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteObjects
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts

object Color {
    fun getObjectAvailabilityColorFilter(available: Boolean, isDarkTheme: Boolean): ColorFilter {
        return if (available) {
            if (isDarkTheme) ColorFilter.tint(Color.White) else ColorFilter.tint(kilidPrimaryColor)

        } else {
            ColorFilter.tint(
                if (isDarkTheme) kilidWhiteObjects.copy(alpha = 0.4f) else kilidDarkObjects.copy(
                    alpha = 0.4f
                )
            )
        }
    }

    fun getTextAvailabilityColorFilter(
        available: Boolean,
        isDarkTheme: Boolean
    ): androidx.compose.ui.graphics.Color {
        return if (available) {
            if (isDarkTheme) Color.White else kilidPrimaryColor
        } else {
            if (isDarkTheme) kilidDarkTexts.copy(alpha = 0.4f) else kilidWhiteTexts.copy(
                alpha = 0.4f
            )
        }
    }


}