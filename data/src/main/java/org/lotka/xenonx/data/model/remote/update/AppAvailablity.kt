package org.lotka.xenonx.data.model.remote.update


import androidx.annotation.Keep


@Keep

data class AppAvailablity(
    
    var body: String,
    
    var buttonText: String,
    
    var buttonUrl: String,
    
    var image: String,
    
    var isAppAvailable: Boolean,
    
    var secondText: String,
    
    var title: String
)