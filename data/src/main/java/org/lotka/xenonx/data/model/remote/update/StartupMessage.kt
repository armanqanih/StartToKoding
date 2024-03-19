package org.lotka.xenonx.data.model.remote.update


import androidx.annotation.Keep


@Keep

data class StartupMessage(
    
    var body: String,
    
    var buttonText: String,
    
    var buttonUrl: String,
    
    var closeable: Boolean,
    
    var enabled: Boolean,
    
    var image: String,
    
    var secondText: String,
    
    var title: String
)