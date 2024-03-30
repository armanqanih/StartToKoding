package org.lotka.xenonx.data.api


import org.lotka.xenonx.data.model.remote.contactInfo.ContactInformationRemote
import org.lotka.xenonx.data.model.remote.location.LocationSearchResponseRemote

import org.lotka.xenonx.data.model.remote.update.AppStatusResponseRemote
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface HomeService {

    @Headers(
        "COUNTRY_ID: 2",
        "LOCALE: FA",
        "Content-Type: application/json"
    )

    @GET("listing_api/v1.0/gListing/single/track")
    suspend fun pdpCountTracker(
        @Query("Identifier") identifier: Int,
        @Query("lang") lang: String = "FA"
    ): Boolean // Replace Boolean with your actual data model class for the response

    @GET("glocation_api/v1.0/fuzzySearch/country")
    suspend fun fuzzySearchCountry(
        @Query("phrase") phrase: String,
        @Query("countryId") countryId: Int = 2
    ): LocationSearchResponseRemote // Replace 'YourResponseType' with the actual data model class for the response

    @Headers(
        "COUNTRY_ID: 2",
        "LOCALE: FA",
        "Content-Type: application/json"
    )
    @GET("listing_api/v1.0/gListing/contactInfo")
    suspend fun getContactInfo(
        @Query("lang") lang: String = "FA",
        @Query("Identifier") identifier: Int,
        @Query("mode") mode: String = "FULL_VIEW"
    ): ContactInformationRemote; // Replace ContactInfoRemote with your actual data model class for the contact info response


    @GET("/android/android_status_v1.json") // static route
    suspend fun checkAppStatus(): AppStatusResponseRemote

}
