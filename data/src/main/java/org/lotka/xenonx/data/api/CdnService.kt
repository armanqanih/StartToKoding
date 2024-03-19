package org.lotka.xenonx.data.api


import org.lotka.xenonx.data.model.remote.update.AppStatusResponseRemote
import retrofit2.http.GET


interface CdnService {
    @GET("/android/portal_status.json") // static route
    suspend fun checkAppStatus(): AppStatusResponseRemote

}
