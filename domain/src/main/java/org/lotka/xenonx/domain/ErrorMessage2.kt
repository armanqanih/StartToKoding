package org.lotka.xenonx.domain



import org.lotka.xenonx.domain.util.HttpErrors

data class ErrorMessage2(
    
    var `data`: String? = "",
    
    var error: ErrorDetail? = ErrorDetail("", "", ""),
    
    var traceId: String? = "",
    val status: HttpErrors,
)