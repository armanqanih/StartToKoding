package org.lotka.xenonx.presentation.ui.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.lotka.xenonx.presentation.ui.navigation.HomeScreensNavigation
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.ChatListViewModel
import org.lotka.xenonx.presentation.ui.screens.chat.pp_chat.SingleChatViewModel

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    private val viewModel by viewModels<MainViewModel>()
    private val chatListViewModel by viewModels<ChatListViewModel>()
    private val singleChatViewModel by viewModels<SingleChatViewModel>()


//    @Inject
//    lateinit var settingsDataStore: SettingsDataStore
//
//    @Inject
//    lateinit var dataStoreManager: DataStoreManager

//    @Inject
//    lateinit var customUpdateManager: CustomUpdateManager
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val keyboardController = LocalSoftwareKeyboardController.current
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                if (keyboardController != null) {
                    HomeApp(
                        activity = this@HomeActivity,
                        viewModel = viewModel,
                        navController = navController,
                        chatListViewModel = chatListViewModel,
                        onNavigateToRecipeDetailScreen = { navController.navigate(HomeScreensNavigation.single_chat_screen.route) },
                        isDarkTheme = false,
                        onToggleTheme = { },
                        singeChatViewModel = singleChatViewModel,
                        keyboardController = keyboardController
                    )
                }
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
