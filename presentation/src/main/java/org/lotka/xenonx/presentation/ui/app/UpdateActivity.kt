package org.lotka.xenonx.presentation.ui.app

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import org.lotka.xenonx.presentation.R
import org.lotka.xenonx.presentation.composables.SetStatusBarColor
import org.lotka.xenonx.presentation.composables.etc.MobinButton
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.ui.navigation.BackPressHandler
import org.lotka.xenonx.presentation.ui.navigation.onBackPressedFunctionToFinishApp
import javax.inject.Inject
import kotlin.system.exitProcess


@AndroidEntryPoint
class UpdateActivity : AppCompatActivity() {

//    @Inject
//    lateinit var dataStoreManager: DataStoreManager

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics



    private var isForceUpdate = false

    @OptIn(
        ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
        ExperimentalPagerApi::class, ExperimentalComposeUiApi::class
    )
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getStringExtra("type") == "FORCE") {
            isForceUpdate = true
        } else if (intent.getStringExtra("type") == "OPTIONAL") {
            isForceUpdate = false
        }

        setContent {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                var coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                //back pressed handler
                val onBack = { onBackPressedFunctionToFinishApp(context) }
                BackPressHandler(onBackPressed = onBack)

                SetStatusBarColor(White)
//                var changes = customUpdateManager.update_features

                Scaffold(
                    topBar = { },
                    content = {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {

                            Spacer(modifier = Modifier.height(46.dp))
                            val composition1 by rememberLottieComposition(
                                LottieCompositionSpec.RawRes(
                                    R.raw.google_play
                                )
                            )
                            val progress1 by animateLottieCompositionAsState(
                                composition1,
                                iterations = 50,
                                speed = 0.8f,
                                reverseOnRepeat = true
                            )
                            LottieAnimation(
                                composition = composition1,
                                progress = { progress1 },
                                modifier = Modifier
                                    .size(200.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 32.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "نسخه جدید اپلیکیشن در دسترس است",
                                    style = KilidTypography.h5
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                if (isForceUpdate) {
                                    Text(
                                        text = "نصب این نسخه اجباری است",
                                        style = KilidTypography.h3,
                                        modifier = Modifier.align(
                                            Alignment.CenterHorizontally
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(46.dp))
                                Text(
                                    text = "تغییرات این نسخه :",
                                    style = KilidTypography.h3,
                                    modifier = Modifier.align(
                                        Alignment.Start
                                    )
                                )

//                                KilidBulleteItem(items = changes)

                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "در این آپدیت علاوه بر ویژگی های جدید ، مشکلات و خطا های نسخه قبلی برطرف شده و تجربه کاربری بهتری را به شما اراِئه میکند ",
                                    style = KilidTypography.h2,
                                    color = Color.Gray,
                                    modifier = Modifier.align(
                                        Alignment.Start
                                    )
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                MobinButton(
                                    title = "آپدیت از طریق  پلی استور",
                                    onClick = {
                                        val appId: String =
                                            "org.lotka.portal" // replace with your app's package name
                                        val intent =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse("market://details?id=$appId")
                                            )
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    },
                                    painter = rememberAsyncImagePainter(model = R.drawable.google_play),
                                    outline = false
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                if (isForceUpdate) {
                                    MobinButton(
                                        title = "خروج از برنامه",
                                        onClick = {
                                            finishAffinity()
                                            exitProcess(0)
                                        },
                                        outline = true
                                    )
                                } else {
                                    MobinButton(
                                        title = "بعدا ",
                                        onClick = {
//                                            coroutineScope.launch {
//                                                //get next 2 hours time in miliseconds
//                                                val wasSuccessful = dataStoreManager.updateData2(
//                                                    DataStoreManager.PreferenceKeys.POSTPONE_UPDATE_TIME,
//                                                    DateUtil.minutesFromNowToMillis(1)
//                                                )
//
//                                                // Here you can use the wasSuccessful boolean
//                                                if (wasSuccessful) {
//                                                    firebaseAnalytics.logEvent(
//                                                        "ANDROID_UPDATE_POSTPONED",
//                                                        null
//                                                    )
//                                                    val intent = Intent(
//                                                        this@UpdateActivity,
//                                                        HomeActivity::class.java
//                                                    )
//                                                    startActivity(intent)
//                                                    finishAffinity()
//                                                }
//                                            }


                                        },
                                        outline = true
                                    )
                                }

                                Spacer(modifier = Modifier.height(32.dp))


                            }


                        }

                    })
            }


        }
    }
}