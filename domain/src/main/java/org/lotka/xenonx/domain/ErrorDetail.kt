package org.lotka.xenonx.domain


import android.os.Parcelable




data class ErrorDetail(
    
    var code: String = "",
    
    var detail: String? = "",
    
    var msg: String? = ""
)