package org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.compose


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.lotka.xenonx.presentation.composables.etc.MobinButton
import org.lotka.xenonx.presentation.composables.etc.general.LottieLoading
import org.lotka.xenonx.presentation.theme.KilidTypography
import org.lotka.xenonx.presentation.theme.TelegramBackGround
import org.lotka.xenonx.presentation.theme.TelegrampinkColor
import org.lotka.xenonx.presentation.theme.kilidDarkBackgound
import org.lotka.xenonx.presentation.theme.kilidDarkTexts
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.theme.kilidWhiteBackgound
import org.lotka.xenonx.presentation.theme.kilidWhiteTexts
import org.lotka.xenonx.presentation.ui.navigation.HomeScreensNavigation
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.ChatListScreenEvent
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.PAGE_SIZE
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.PlpViewModel
import org.lotka.xenonx.presentation.util.UIState
import timber.log.Timber
import java.util.Collections


@Composable
fun HomeTabRow(
    navController: NavController,
    viewModel: PlpViewModel,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {
    var selectedTabIndex by remember { mutableIntStateOf(1) }

    val tabs = listOf(
        "Pv",
        "All",

    )

    val tabContent: @Composable (Int) -> Unit = { tabIndex ->
        when (tabIndex) {
            1 -> All(
                navController = navController,
                viewModel = viewModel,
                onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen,
                onToggleTheme = onToggleTheme,
                isDarkTheme = isDarkTheme
            )

            0 -> Pv(
                navController = navController,
                viewModel = viewModel,
                onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen,
                onToggleTheme = onToggleTheme,
                isDarkTheme = isDarkTheme
            )

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = TelegrampinkColor,
            divider = {

                Divider(color = Color.Gray, thickness = 1.dp)
            },
            indicator = { tabPositions ->

                TabRowDefaults.Indicator(
                    color = TelegrampinkColor,
                    height = 4.dp,
                    modifier = Modifier
                        .width(10.dp) // Set the width to 10dp
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                )


            },
            backgroundColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            tabs.forEachIndexed { index, title ->
                val modifier = if (selectedTabIndex == index) {
                    Modifier
                        .height(48.dp)
                        .width(10.dp)
                } else {
                    Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                }
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = modifier

                ) {
                    Text(
                        text = title,
                        color = if (selectedTabIndex == index) TelegrampinkColor else Color.Gray,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        tabContent(selectedTabIndex)
    }
}

@Composable
fun Pv() {
    TODO("Not yet implemented")
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Pv(
    navController: NavController,
    viewModel: PlpViewModel,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
) {

    val page = viewModel.page.value
    val loading = viewModel.isActiveJobRunning.value

    val configuration = LocalConfiguration.current

    val sessions by viewModel.sessions.collectAsState(initial = Collections.emptyList())
    val uiState by viewModel.sessionUiState.collectAsState(initial = UIState.Idle)

    val lazyListState = rememberLazyListState()



    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) TelegramBackGround else TelegramBackGround),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when (uiState) {
            is UIState.Error -> {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(
                            (configuration.screenHeightDp.dp / 3) * 2
                        )

                    ) {
                        Text(
                            text = "خطا در ارتباط با شبکه",
                            style = KilidTypography.h4.copy(fontSize = 18.sp),
                            color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                        )
                        Text(
                            text = "لطفا اتصال به اینترنت را بررسی کنید ",
                            style = KilidTypography.h3.copy(fontSize = 14.sp),
                            color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                        )
                        Spacer(modifier = Modifier.height(16.dp))


                        MobinButton(
                            title = "تلاش مجدد",
                            onClick = {
                                viewModel.onTriggerEvent(
                                    ChatListScreenEvent.NewSearchEvent
                                )
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

                if (false) {
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

                            if (false) {

                                Text(
                                    text = "شما هیچ آگهی ندارید \n از طریق دکمه زیر آگهی ثبت کنید",
                                    style = KilidTypography.h3,
                                    textAlign = TextAlign.Center
                                )

                            } else {

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "برای این دسته بندی آگهی وجود ندارد",
                                        style = KilidTypography.h3
                                    )

                                    Text(
                                        text = "حذف همه فیلتر ها",
                                        style = KilidTypography.h3.copy(
                                            color = Color.Blue
                                        ),
                                        modifier = Modifier.clickable {
                                        }
                                    )
                                }

                            }
                        }
                    }
                } else {

                    itemsIndexed(items = sessions) { index, recipe ->
                        viewModel.latestIndex.value = index
                        if (uiState != UIState.PaginationError) {
                            viewModel.onChangeRecipeScrollPosition(index)

                            if (((index + 1) >= PAGE_SIZE) && (page == 0) && !loading) {
                                Timber.tag("pagination")
                                    .d("for zero page --- latest index : " + index + " and ")
                                viewModel.onTriggerEvent(ChatListScreenEvent.NextPageEvent)
                            } else if ((index + 3) >= (((page + 1) * PAGE_SIZE)) && !loading) {
//                                                            Timber.tag("pagination").d("for ${viewModel.calculateOutput(page)} other page --- latest index : " + index + " and page is: ${page} and multiple ${(page * PAGE_SIZE)} ")
                                viewModel.onTriggerEvent(ChatListScreenEvent.NextPageEvent)
                            }

                        }




                        if (recipe != null) {

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
                                    viewModel.savedScrollIndex = index
                                    val route =
                                        HomeScreensNavigation.pdp.route + "/${it}"
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun All(
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
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isDarkTheme) kilidDarkBackgound else kilidWhiteBackgound),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when (uiState) {
            is UIState.Error -> {
                item {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(
                            (configuration.screenHeightDp.dp / 3) * 2
                        )

                    ) {
                        Text(
                            text = "خطا در ارتباط با شبکه",
                            style = KilidTypography.h4.copy(fontSize = 18.sp),
                            color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                        )
                        Text(
                            text = "لطفا اتصال به اینترنت را بررسی کنید ",
                            style = KilidTypography.h3.copy(fontSize = 14.sp),
                            color = if (isDarkTheme) kilidDarkTexts else kilidWhiteTexts
                        )
                        Spacer(modifier = Modifier.height(16.dp))


                        MobinButton(
                            title = "تلاش مجدد",
                            onClick = {
                                viewModel.onTriggerEvent(
                                    ChatListScreenEvent.NewSearchEvent
                                )
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

                if (false) {
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

                            if (false  ) {

                                Text(
                                    text = "شما هیچ آگهی ندارید \n از طریق دکمه زیر آگهی ثبت کنید",
                                    style = KilidTypography.h3,
                                    textAlign = TextAlign.Center
                                )

                            } else {

                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "برای این دسته بندی آگهی وجود ندارد",
                                        style = KilidTypography.h3
                                    )

                                    Text(
                                        text = "حذف همه فیلتر ها",
                                        style = KilidTypography.h3.copy(
                                            color = Color.Blue
                                        ),
                                        modifier = Modifier.clickable {
                                        }
                                    )
                                }

                            }
                        }
                    }
                } else {

                    itemsIndexed(items = sessions) { index, recipe ->
                        viewModel.latestIndex.value = index
                        if (uiState != UIState.PaginationError) {
                            viewModel.onChangeRecipeScrollPosition(index)

                            if (((index + 1) >= PAGE_SIZE) && (page == 0) && !loading) {
                                Timber.tag("pagination")
                                    .d("for zero page --- latest index : " + index + " and ")
                                viewModel.onTriggerEvent(ChatListScreenEvent.NextPageEvent)
                            } else if ((index + 3) >= (((page + 1) * PAGE_SIZE)) && !loading) {
//                                                            Timber.tag("pagination").d("for ${viewModel.calculateOutput(page)} other page --- latest index : " + index + " and page is: ${page} and multiple ${(page * PAGE_SIZE)} ")
                                viewModel.onTriggerEvent(ChatListScreenEvent.NextPageEvent)
                            }

                        }




                        if (recipe != null) {

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
                                    viewModel.savedScrollIndex = index
                                    val route =
                                        HomeScreensNavigation.pdp.route + "/${it}"
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








