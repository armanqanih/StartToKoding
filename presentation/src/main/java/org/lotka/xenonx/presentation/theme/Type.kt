package org.lotka.xenonx.presentation.theme


import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.ainarm.chat.presentation.R


val yekanBakh = FontFamily(
    Font(R.font.ubuntu_light, FontWeight.Light),
    Font(R.font.ubuntu_regular, FontWeight.Normal),
    Font(R.font.ubuntu_medium, FontWeight.Medium),
    Font(R.font.ubuntu_bold, FontWeight.Bold),
    Font(R.font.ubuntu_bold, FontWeight.Black),
)


// Set of Material typography styles to start with
val KilidTypography = Typography(
    h6 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Black,
        color = newPrimaryTextColor

    ),
    h5 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Bold,
        color = newPrimaryTextColor
    ),
    h4 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.SemiBold,
        color = newPrimaryTextColor
    ),
    h3 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Medium,
        color = newPrimaryTextColor
    ),
    h2 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Normal,
        color = newPrimaryTextColor
    ),
    h1 = TextStyle(
        fontFamily = yekanBakh,
        fontWeight = FontWeight.Light,
        color = newThirdTextColor,
    ),
)