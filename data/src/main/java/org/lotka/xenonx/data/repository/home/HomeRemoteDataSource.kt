package org.lotka.xenonx.data.repository.home


import org.lotka.xenonx.domain.enums.IsItemPinnedStatus
import org.lotka.xenonx.domain.enums.UserVerificationStatus
import org.lotka.xenonx.domain.model.model.contactInfo.ContactInformation
import org.lotka.xenonx.domain.model.model.location.LocationSearchModel
import org.lotka.xenonx.domain.model.model.pdp.PdpModel
import org.lotka.xenonx.domain.model.model.plp.PlpItemResultModel
import org.lotka.xenonx.domain.model.model.plp.PlpResponseModel
import org.lotka.xenonx.domain.model.model.update.AppStatusResponse
import org.lotka.xenonx.domain.util.ResultState
import org.lotka.xenonx.domain.util.Sektorum
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min

@Singleton
class HomeRemoteDataSource @Inject constructor(
    @Sektorum private val apiService: org.lotka.xenonx.data.api.HomeService,
    private val cdnService: org.lotka.xenonx.data.api.CdnService,
    private val apiExceptionHandler: org.lotka.xenonx.data.exceptions.NetworkExceptionHandler,
) : HomeDataSource {
    override suspend fun searchLocation(text: String): ResultState<LocationSearchModel> {
        return try {
            val result = apiService.fuzzySearchCountry(phrase = text)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


    override suspend fun pdpDetail(id: Int): ResultState<PdpModel> {
        return try {
            val result = apiService.getSingleListing(identifier = id)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun pdpCountTracker(id: Int): ResultState<Boolean> {
        return try {
            val result = apiService.pdpCountTracker(identifier = id)
            ResultState.Success(result)
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


    override suspend fun loadPlpList(
        page: Int,
    ): ResultState<PlpResponseModel> {

        val allItems = List(10000) { index ->
            // Calculate image number within the range 3000 to 4000
            val imageNumber =
                3000 + (index % 1001) // Adjusted to cycle through 3000 to 4000 correctly
            PlpItemResultModel(
                id = index,
                userFirstName = "User$index",
                userLastName = "Last$index",
                smallProfileImage = "https://ozgrozer.github.io/100k-faces/0/3/00${imageNumber}.jpg",
                lastMessageText = "Hello from User$index",
                numUnreadMessage = index % 3,
                lastTypingDate = "10:01",
                isPremiumUser = index % 2 == 0,
                lastMessageType = "text",
                lastChatSeenDate = System.currentTimeMillis() - (1000 * 60 * 60 * 24 * index),
                lastMessageStatus = "sent",
                hasStory = index % 2 == 1,
                lastMessageDate = "15:00",
                isItemPinned = when (index) {
                    1, 2, 3 -> IsItemPinnedStatus.ITEMPINNED
                    4, 6 -> IsItemPinnedStatus.NONE
                    else -> IsItemPinnedStatus.MESSAGENUMBER
                },
                verificationStatus =

                when (index) {
                    0 -> UserVerificationStatus.BLUE_VERIFIED
                    4 -> UserVerificationStatus.GREEN_VERIFIED
                    else -> UserVerificationStatus.NONE
                }, isSilent = when (index) {
                    0, 4, 7 -> true
                    else -> false
                },
                isTyping = when (index) {
                    0, 1, 2 -> true
                    else -> false
                },
                isLockAccount = when (index) {
                    0, 5 -> true
                    else -> false
                },
                isSentAPicture = when (index) {
                    4, 5, 6 -> true
                    else -> false
                },
                isUnreadMessage = when (index) {
                    0, 2, 3, 6, 8, 10, 12 -> true
                    else -> false
                }


            )
        }

        // Assuming each page shows 20 items
        val pageSize = 23
        // Adjust calculation for startIndex to accommodate page starting at 0
        val startIndex = page * pageSize
        // Calculate the endIndex for the given page
        val endIndex = min(startIndex + pageSize, allItems.size)

        return try {
            // Check if the page number is within the valid range
            if (startIndex < allItems.size) {
                // Extract the sublist for the requested page
                val pageItems = allItems.subList(startIndex, endIndex)
                // Assuming PlpResponseModel can directly take the sublist of items
                val responseModel = PlpResponseModel(pageItems, 10000)
                ResultState.Success(responseModel)
            } else {
                // Handle case where the page number is out of range
                ResultState.Error(apiExceptionHandler.traceErrorException(Throwable("Page number out of range")))
            }
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun getContactInformation(identifier: Int): ResultState<ContactInformation> {
        return try {
            val result = apiService.getContactInfo(identifier = identifier)
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }

    override suspend fun getAppStatus(): ResultState<AppStatusResponse> {
        return try {
            val result = cdnService.checkAppStatus()
            ResultState.Success(result.toDomain())
        } catch (e: Exception) {
            ResultState.Error(apiExceptionHandler.traceErrorException(e))
        }
    }


}