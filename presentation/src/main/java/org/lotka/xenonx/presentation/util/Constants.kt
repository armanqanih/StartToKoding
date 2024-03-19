package org.lotka.xenonx.presentation.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData

//constatns
const val BASE_URL = "https://api.kilid.com/"
const val OTP_LENGTH = 5
const val OTP_RECEIVE_TIMEOUT = 120000L


var listFirstVisibleItemIndex by mutableStateOf(0)
var listFirstVisibleItemScrollOffset by mutableStateOf(0)


fun updateListingAndClearSavedListingPosition() {
    listingTimeStamp.postValue(System.currentTimeMillis())
    listFirstVisibleItemIndex = 0
    listFirstVisibleItemScrollOffset = 0
}

val listingTimeStamp = MutableLiveData<Long>()

// ------------- City & Region & agent selector ------------
//var city: City? = null
//var region: RegionResult? = null
//val agent = MutableStateFlow<ActiveUserWithMinimalDetails?>(null)


// --------------------- App Update -------------------
/*
1. No. 71 was the first edition that was published
2. The first parameter (String) is the last version for which the update message is shown to the user
3. The second parameter (Boolean) tells whether the user wants to receive the offer again or not
 */
var suggestToDownloadTheNewVersion: Pair<String, Boolean> = Pair("71", true)

//var updateResponse: MutableState<UpdateAppInfoResponse?> = mutableStateOf(null)



// ------------------------ Listing ---------------------


var listingFilterIsOn: Boolean = false
//var listingFilterParams = Filter()


// Pagination
const val PAGINATION_PAGE_SIZE = 10
const val PAGINATION_FIRST_PAGE_INDEX = 0
const val MAX_IMAGE_COUNT = 8


// ------------------------ LMS ---------------------
const val visibleConditionsInLMS = 6
const val DurationOfNumberVisibility =
    86400000  // in milliseconds, current value = 3 min, After this time, the homeseeker's number will be hidden

var homeSeekerFilterIsOn: Boolean = false
//var homeSeekerFilterParams = HomeSeekerFilter()


// ----------------------- static links ---------------------------
const val userManualLink: String =
    "https://cdn.kilid.com/kilid.pro/manual/KiliDUserManualV1.4.0.pdf"
const val defaultUserAvatar: String = "https://cdn.kilid.com/default-photo.png"

