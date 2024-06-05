package org.lotka.xenonx.presentation.ui.screens.plp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.model.PlpItemResultModel
import org.lotka.xenonx.presentation.ui.screens.plp.bottom_sheet.PlpBottomSheetType
import org.lotka.xenonx.presentation.util.DialogQueue
import org.lotka.xenonx.presentation.util.UIState
import javax.inject.Inject


const val PAGE_SIZE = 23

const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"


@HiltViewModel
class PlpViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,

) :ViewModel() {


    var isIndicatedUpdateAvailable by mutableStateOf(false)
//    val searchAreaResult = MutableStateFlow<List<LocationSearchItem?>>(emptyList())
    val searchAreaUiState = MutableStateFlow<UIState>(UIState.Success)
    val sessions = MutableStateFlow<List<PlpItemResultModel?>>(emptyList())

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

    fun showBottomSheet(type: PlpBottomSheetType) {
        viewModelScope.launch{
            if(type== PlpBottomSheetType.LOCATION_SEARCH){
                fullScreenActiveBottomSheet=type
            }else{
                halfScreenActiveBottomSheet=(type)
            }
        }
    }





    }












