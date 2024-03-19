package org.lotka.xenonx.domain.model.model.update


import androidx.annotation.Keep


@Keep
data class Versions(
    // if there is a problem in latest release , turn off  all update functionality
    // by turn off  "update_functionality" users never will be notified about update
    
    var updateFunctionality: Boolean,
    //every user with VPN can update app
    
    var autoUpdateEnabled: Boolean,
    //just show update in settings screen with a red dot , and show in dashboard
    
    var indicatedUpdate: Int,
    //show optional update screen in startup
    
    var optionalUpdate: Int,
    //force user to update app
    
    var minimum_available_version: Int,
    //list of persian String about new features
    
    var updateFeatures: List<String>,
    //if there is a problem with google play , user can download app from this link
    
    var downloadLink: String,
)