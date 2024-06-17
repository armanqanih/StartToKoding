package org.lotka.xenonx.presentation.composables.etc.general

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.ainarm.chat.presentation.R


@Composable
fun LottieLoading(size: Dp) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 100,
        reverseOnRepeat = true
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .size(size)

    )
}