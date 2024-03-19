package org.lotka.xenonx.data.model.remote.update


import androidx.annotation.Keep


@Keep

data class DashboardMessage(
    
    var body: String,
    
    var buttonText: String,
    
    var buttonUrl: String,
    
    var dismissible: Boolean,
    
    var enabled: Boolean,
    
    var image: String,
    
    var isURL: Boolean,
    
    var secondText: String,
    
    var title: String
)