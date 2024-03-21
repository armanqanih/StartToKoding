package org.lotka.xenonx.presentation.ui.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.kilid.portal.presentation.ui.app.HomeApp
import dagger.hilt.android.AndroidEntryPoint
import org.lotka.xenonx.presentation.ui.navigation.HomeScreensNavigation
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.ChatListViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    private val viewModel by viewModels<MainViewModel>()
    private val chatListViewModel by viewModels<ChatListViewModel>()


//    @Inject
//    lateinit var settingsDataStore: SettingsDataStore
//
//    @Inject
//    lateinit var dataStoreManager: DataStoreManager

//    @Inject
//    lateinit var customUpdateManager: CustomUpdateManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                HomeApp(
                    activity = this@HomeActivity,
                    viewModel = viewModel,
                    navController = navController,
                    plpviewModel = chatListViewModel,
                    onNavigateToRecipeDetailScreen = { navController.navigate(HomeScreensNavigation.SingleChatScreen.route) },
                    isDarkTheme = false,
                    onToggleTheme = { },

                    )
            }
        }


//        lifecycleScope.launch {
//            customUpdateManager.updateStatus.collectLatest {
//                Timber.tag("updateLogger").d("updateStatus in main actvity: %s", it.toString())
//                when (it) {
//                    CustomUpdateManager.UpdateType.FORCE_UPDATE -> {
//
//                        val intent = Intent(this@HomeActivity, UpdateActivity::class.java)
//                        intent.flags =
//                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        intent.putExtra("type", "FORCE")
//                        startActivity(intent)
//                        finishAffinity()
//                    }
//
////                    CustomUpdateManager.UpdateType.OPTIONAL_UPDATE -> {
////                        val postponedDate = dataStoreManager.postponeUpdateFlow.first()
////                        if (postponedDate != null) {
////                            if (dataStoreManager.postponeUpdateFlow.first()!! <= System.currentTimeMillis()) {
////                                val intent = Intent(this@HomeActivity, UpdateActivity::class.java)
////                                intent.flags =
////                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////                                intent.putExtra("type", "OPTIONAL")
////                                startActivity(intent)
////                                finishAffinity()
////                            }
////                        }
////                    }
//
//                    else -> {}
//                }
//            }
//        }


    }


}
