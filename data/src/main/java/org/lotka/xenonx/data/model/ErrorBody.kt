package org.lotka.xenonx.data.model


import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.lotka.xenonx.data.base.ResponseObject
import org.lotka.xenonx.domain.ErrorDetail
import org.lotka.xenonx.domain.ErrorMessage2
import org.lotka.xenonx.domain.util.HttpErrors

@SuppressLint("ParcelCreator")

data class ErrorBody(
    val data: String?,
    val error: ErrorDetail,
    var traceId: String?,
    @IgnoredOnParcel
    var status: Int? = 200,
) : ResponseObject<ErrorMessage2> {
    override fun toDomain(): ErrorMessage2 {
        return ErrorMessage2(
            data = data,
            error = error,
            traceId = traceId,
            status = when (status) {
                400 -> HttpErrors.BadRequest
                401 -> HttpErrors.Unauthorized//Token Expired
                403 -> HttpErrors.Forbidden//Not permission
                404 -> HttpErrors.NotFound
                406 -> HttpErrors.NotAcceptable
                409 -> HttpErrors.Conflict
                500 -> HttpErrors.ServerError
                else -> HttpErrors.NotDefined
            }
        )
    }
}


data class BackendError(
    val code: String,
    val msg: String,
    val detail: String
)