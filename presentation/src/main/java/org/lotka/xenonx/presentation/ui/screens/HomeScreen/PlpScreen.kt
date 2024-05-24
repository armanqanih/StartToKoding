package org.lotka.xenonx.presentation.ui.screens.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.lotka.xenonx.presentation.composables.HomeScreensNavigation
import org.lotka.xenonx.presentation.composables.etc.MobinButton
import org.lotka.xenonx.presentation.composables.etc.general.LottieLoading
import org.lotka.xenonx.presentation.theme.AuthTheme
import org.lotka.xenonx.presentation.theme.Gray
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.White
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteBackgound
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts
import org.lotka.xenonx.presentation.ui.app.onBackPressedFunctionToFinishApp
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.bottom_sheet.ListingItemBottomSheet
import org.lotka.xenonx.presentation.ui.screens.HomeScreen.bottom_sheet.PlpBottomSheetType
import org.lotka.xenonx.presentation.util.UIState
import timber.log.Timber
import java.util.Collections

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun PlpScreen(
    navController: NavController,
    viewModel: PlpViewModel,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {

    var canPop by remember { mutableStateOf(false) }
    val page = viewModel.page.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val dialogQueue = viewModel.dialogQueue
    val loading = viewModel.isActiveJobRunning.value
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

   val sessions by viewModel.sessions.collectAsState(initial = Collections.emptyList())
    val uiState by viewModel.sessionUiState.collectAsState(initial = UIState.Idle)

    val lazyListState = rememberLazyListState()
    val savedScrollIndex = viewModel.savedScrollIndex


    LaunchedEffect(key1 = savedScrollIndex) {
        Timber.tag("pdptest").d("in first launchd effect: ")
        lazyListState.scrollToItem(savedScrollIndex)
    }

    // Create the ModalBottomSheetState with dynamic skipHalfExpanded value
    val halfScreenBottomSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = false
    )

    val fullScreenBottomSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val onBack: () -> Unit = {
        coroutineScope.launch {
            if (fullScreenBottomSheet.currentValue != ModalBottomSheetValue.Hidden) {
                viewModel.showBottomSheet(PlpBottomSheetType.NONE)
                fullScreenBottomSheet.hide()
                return@launch
            } else if (halfScreenBottomSheet.currentValue != ModalBottomSheetValue.Hidden) {
                viewModel.showBottomSheet(PlpBottomSheetType.NONE)
                halfScreenBottomSheet.hide()
            } else {
                onBackPressedFunctionToFinishApp(context)
            }
        }
    }






    ModalBottomSheetLayout(
        sheetState = halfScreenBottomSheet,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Gray,
        sheetElevation = 30.dp,
        sheetContent = {
            when (viewModel.halfScreenActiveBottomSheet) {
                PlpBottomSheetType.LISTING_ITEM -> {
                    ListingItemBottomSheet(
                        viewModel, halfScreenBottomSheet, coroutineScope
                    )
                }

                else -> {

                }
            }
        },


        ) {


        AuthTheme(
            darkTheme = isDarkTheme,
            isNetworkAvailable = true,
            displayProgressBar = loading,
            scaffoldState = scaffoldState,
            dialogQueue = dialogQueue.queue.value

        ) {

            Scaffold(topBar = {
//                HomeTopBar(
//                    onClick = {},
//                    mainScreens = true,
//                    onToggleTheme = {
//                        onToggleTheme()
//                    },
//                    onBackPressed = onBack,
//                    isDarkMode = isDarkTheme,
//                )
            },
                content = {
                    val swipeRefreshState = rememberPullRefreshState(
                        refreshing = uiState == UIState.Loading,
                        onRefresh = {
//                            viewModel.onTriggerEvent(PlpScreenEvent.NewSearchEvent)
                        }
                    )

                    Column(modifier = Modifier.background(
                        if (isDarkTheme) kilidDarkBackgound else kilidWhiteBackgound)) {
                        val context = LocalContext.current

                        AnimatedVisibility(
                            visible = viewModel.isIndicatedUpdateAvailable,
                            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) +
                                    expandVertically(animationSpec = tween(durationMillis = 1000)),
                            exit = fadeOut(animationSpec = tween(durationMillis = 1000)) +
                                    shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .background(kilidPrimaryColor)
                                .clickable {
                                }) {
                                Divider(color = White.copy(alpha = 0.3f))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(kilidPrimaryColor)
                                        .padding(vertical = 8.dp, horizontal = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "New Update",
                                        style = KilidTypography.h3,
                                        color = White
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Install",
                                        style = KilidTypography.h3,
                                        color = White
                                    )
                                }
                            }

                        }


                        Divider()

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .pullRefresh(swipeRefreshState)
                        )
                        {


                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (isDarkTheme) kilidDarkBackgound else kilidWhiteBackgound),
                                state = lazyListState,
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = CenterHorizontally
                            ) {

                                when (uiState) {
                                    is UIState.Error -> {
                                        item {
                                            Column(
                                                verticalArrangement = Center,
                                                horizontalAlignment = CenterHorizontally,
                                                modifier = Modifier.height(
                                                    (configuration.screenHeightDp.dp / 3) * 2
                                                )

                                            ) {
                                                Text(
                                                    text = "We Have an Error in Internet Connection",
                                                    style = KilidTypography.h4.copy(fontSize = 18.sp),
                                                    color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                                                )
                                                Text(
                                                    text = "Please connect to the internet and try again",
                                                    style = KilidTypography.h3.copy(fontSize = 14.sp),
                                                    color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                                                )
                                                Spacer(modifier = Modifier.height(16.dp))


                                                MobinButton(
                                                    title = "Try Again",
                                                    onClick = {
//                                                        viewModel.onTriggerEvent(
//                                                            PlpScreenEvent.NewSearchEvent
//                                                        )
                                                    },
                                                    outline = true,
                                                    modifier = Modifier
                                                        .height(40.dp)
                                                        .padding(horizontal = 32.dp)
                                                        .border(
                                                            BorderStroke(
                                                                width = 2.dp,
                                                                color = kilidPrimaryColor
                                                            ), RoundedCornerShape(8.dp)
                                                        )
                                                )
                                            }
                                        }
                                    }

                                    UIState.Loading -> {
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .height((configuration.screenHeightDp.dp / 3) * 2)
                                                    .fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LottieLoading(size = 150.dp)
                                            }
                                        }
                                    }

                                    else -> {

                                        if (sessions.isEmpty() && uiState == UIState.Success) {
                                            item {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(8.dp)
                                                        .fillMaxWidth()
                                                        .height(
                                                            100.dp
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {

                                                    Text(
                                                        text = "No Results Found",
                                                        style = KilidTypography.h4.copy(fontSize = 18.sp),
                                                        color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                                                    )
                                                }
                                            }
                                        } else {

                                            itemsIndexed(items = sessions) { index, recipe ->

                                                PlpItem(
                                                    isDarkTheme = isDarkTheme,
                                                    item = recipe,
                                                    screen = configuration,
                                                    onMoreClicked = {
//                                                                coroutineScope.launch {
//                                                                    viewModel.showBottomSheet(
//                                                                        PlpBottomSheetType.LISTING_ITEM
//                                                                    )
//                                                                    halfScreenBottomSheet.show()
//                                                                }
                                                    },
                                                    onClicked = {
                                                        viewModel.fullScreenActiveBottomSheet
                                                        viewModel.savedScrollIndex = index
                                                        val route =
                                                            HomeScreensNavigation.ChatHome.route + "/${it}"
                                                        onNavigateToRecipeDetailScreen(route)
                                                    },
                                                    onLadderUpClick = {},
                                                    onFeaturedClick = {},
                                                    index = index
                                                )




                                            }
                                        }


                                    }
                                }


                            }


                        }

                    }
                })
        }
    }
}