package org.lotka.xenonx.presentation.ui.screens.chat.chat_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.model.model.chat.chat_list.ChatListResponseItemModel
import org.lotka.xenonx.domain.model.model.location.LocationSearchItem
import org.lotka.xenonx.domain.usecase.chat.ObserveUserChatListingUseCase
import org.lotka.xenonx.domain.usecase.chat.PdpCountTrackerUseCase
import org.lotka.xenonx.domain.usecase.update.GetUpdateUseCase
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.presentation.ui.app.BaseViewModel
import org.lotka.xenonx.presentation.ui.screens.chat.chat_listing.compose.bottom_sheet.PlpBottomSheetType
import org.lotka.xenonx.presentation.util.DialogQueue
import org.lotka.xenonx.presentation.util.DispatchersProvider
import org.lotka.xenonx.presentation.util.UIState
import timber.log.Timber
import javax.inject.Inject


const val PAGE_SIZE = 23

const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"


@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val observeUserChatListingUseCase: ObserveUserChatListingUseCase,
    private val pdpCountTrackerUseCase: PdpCountTrackerUseCase,

    private val savedStateHandle: SavedStateHandle,
    val updateUseCase: GetUpdateUseCase,

    ) : BaseViewModel(dispatchers) {
 




     var item: ChatListResponseItemModel? = null

    var isIndicatedUpdateAvailable by mutableStateOf(false)
    val searchAreaResult = MutableStateFlow<List<LocationSearchItem?>>(emptyList())
    val searchAreaUiState = MutableStateFlow<UIState>(UIState.Success)

    var filterStateVersion by mutableIntStateOf(0)

    val inChatProsess = mutableStateOf(false)


    private var initialFilterState: String = ""


    fun onFilterBottomSheetClosed() {
        // Compare the current filter state with the stored initial state
        // If they are the same, do nothing
    }






    val sessions = MutableStateFlow<List<ChatListResponseItemModel?>>(emptyList())
    val sessionUiState = MutableStateFlow<UIState>(UIState.Success)




    var halfScreenActiveBottomSheet by mutableStateOf(PlpBottomSheetType.NONE)
    var fullScreenActiveBottomSheet by mutableStateOf(PlpBottomSheetType.NONE)

    // Pagination starts at '1' (-1 = exhausted)
    val page = mutableIntStateOf(0)
    val latestIndex = mutableIntStateOf(0)
    private val totalItems = mutableIntStateOf(0)
    var savedScrollIndex by mutableIntStateOf(0)

    private var recipeListScrollPosition = 0

    val dialogQueue = DialogQueue()
    val isActiveJobRunning = mutableStateOf(false)


    init {
        viewModelScope.launch {
            updateUseCase.invoke().collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        Timber.tag("updateLogger").d("error: ${it.error.error}")
                        //could not connect to cdn or there is no internet connection
                    }
                    is ResultState.Loading -> {}
                    is ResultState.Success -> {
                        Timber.tag("updateLogger").d("Success: ${it.data.toString()}")
                    }

                    else -> {}
                }
            }
        }
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }

        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }


        if (recipeListScrollPosition != 0) {
            onTriggerEvent(ChatListScreenEvent.RestoreStateEvent)
        } else {
            onTriggerEvent(ChatListScreenEvent.NewSearchEvent)
        }


    }


    fun showBottomSheet(type: PlpBottomSheetType) {
        viewModelScope.launch{
            if(type== PlpBottomSheetType.LOCATION_SEARCH){
                fullScreenActiveBottomSheet=type
            }else{
                halfScreenActiveBottomSheet=(type)
            }
        }
    }


    private fun appendRecipes(recipes: List<ChatListResponseItemModel?>) {
        val recipes2 = recipes
        val current = ArrayList(this.sessions.value)
        current.addAll(recipes2)
        this.sessions.value = current
    }


    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    private fun decrementPage() {
        setPage(page.value - 1)
    }

    private fun newSearch() {
        page.intValue = 0
        viewModelScope.launch {
            sessionUiState.emit(UIState.Loading)
            observeUserChatListingUseCase.invoke(
                page = page.intValue,
            ).collect {
                when (it) {
                    is ResultState.Error -> {
                        isActiveJobRunning.value = true
                        sessionUiState.emit(UIState.Error(it.error.error?.msg.toString()))
                        dialogQueue.appendErrorMessage(it.error.error?.msg.toString(), "لطفا وضعیت اینترنت خود را بررسی کنید")
                        isActiveJobRunning.value = false
                    }
                    is ResultState.Loading -> {
                        sessionUiState.emit(UIState.Loading)
                    }
                    is ResultState.Success -> {
                        sessions.value = emptyList()
                        sessionUiState.emit(UIState.Success)
                        it.data?.chatListItemModel?.let { it1 -> appendRecipes(recipes = it1) }
                        totalItems.value = it.data?.total ?: 0
                    }

                    else -> {}
                }
            }
        }
    }

    private fun nextPage() {
        isActiveJobRunning.value = true
        if (totalItems.value > (page.value * PAGE_SIZE)) {
            viewModelScope.launch {
                if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                    incrementPage()
                    if (page.value >= 1) {
                        sessionUiState.emit(UIState.PaginationLoading)
                        observeUserChatListingUseCase.invoke(
                            page = page.value,
                        ).collect {
                            when (it) {
                                is ResultState.Error -> {
                                    sessionUiState.emit(UIState.PaginationError)
                                    decrementPage()
                                    isActiveJobRunning.value = false
                                }
                                is ResultState.Loading -> {
                                    sessionUiState.emit(UIState.PaginationLoading)
                                    isActiveJobRunning.value = true
                                }
                                is ResultState.Success -> {
                                    it.data?.chatListItemModel?.let { it1 -> appendRecipes(recipes = it1) }
                                    totalItems.value = it.data?.total ?: 0
                                    viewModelScope.launch {
                                        delay(1400)
                                        sessionUiState.emit(UIState.Success)
                                        isActiveJobRunning.value = false
                                    }
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }


    }

    fun onTriggerEvent(event: ChatListScreenEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is ChatListScreenEvent.NewSearchEvent -> {
                        newSearch()
                    }
                    is ChatListScreenEvent.NextPageEvent -> {
                        nextPage()
                    }
                    is ChatListScreenEvent.SearchLocationPhrase -> {
                        viewModelScope.launch {
                            pdpCountTrackerUseCase.invoke(12)

                        }
                    }
                    else -> {}
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
            }
        }
    }

    private fun setListScrollPosition(position: Int) {
        recipeListScrollPosition = position + 1
        savedStateHandle[STATE_KEY_LIST_POSITION] = position
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }










}


