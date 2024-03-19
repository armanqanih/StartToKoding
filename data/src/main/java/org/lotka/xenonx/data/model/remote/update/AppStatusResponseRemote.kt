package org.lotka.xenonx.data.model.remote.update


import androidx.annotation.Keep

import org.lotka.xenonx.domain.model.model.update.AppStatusResponse

@Keep

data class AppStatusResponseRemote(
    
    var appAvailablity: AppAvailablity,
    
    var dashboardMessage: DashboardMessage,
    
    var startupMessage: StartupMessage,
    
    var versions: Versions
) : org.lotka.xenonx.data.base.ResponseObject<AppStatusResponse> {
    override fun toDomain(): AppStatusResponse {
        return AppStatusResponse(
            versions = null
        )
    }
}