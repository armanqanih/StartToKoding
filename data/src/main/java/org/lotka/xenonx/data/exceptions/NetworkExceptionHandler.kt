package org.lotka.xenonx.data.exceptions

import com.google.gson.Gson
import okhttp3.ResponseBody
import org.lotka.xenonx.data.model.ErrorBody
import org.lotka.xenonx.domain.ErrorDetail
import org.lotka.xenonx.domain.ErrorMessage2
import org.lotka.xenonx.domain.util.HttpErrors
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkExceptionHandler @Inject constructor(
    private val gson: Gson
) {


    fun traceErrorException(throwable: Throwable?): ErrorMessage2 {
        val errorMessage: ErrorMessage2? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    //sharedPreferences.edit().clear().apply()
                    getHttpError(throwable.response()?.errorBody(), throwable.code())
                }
                /* else if (throwable.code() == 404) {
                     ErrorMessage(
                         status = HttpErrors.Forbidden,
                         message = "You do not have permission to perform this operation"
                     )
                 }*//*else if (throwable.code() == 403) {
                    ErrorMessage(
                        status = HttpErrors.Forbidden,
                        message = "You do not have permission to perform this operation"
                    )
                }*/
                /*  else if (throwable.code() == 500) {
                      ErrorMessage(
                          status = HttpErrors.ServerError,
                          message = "Sorry, the server has a technical problem, please try again in a few moments"
                      )
                  }*/
                else {
                    getHttpError(throwable.response()?.errorBody(), throwable.code())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorMessage2(
                    status = HttpErrors.TimeOut,
                    error = ErrorDetail(detail = throwable.message.toString())
                )
            }

            // handle connection error
            is IOException -> {
                ErrorMessage2(
                    status = HttpErrors.NotFound,
                    error = convertIoExceptions(throwable.cause)
                )
            }

            else -> null
        }
        return errorMessage ?: ErrorMessage2(
            status = HttpErrors.NotDefined,
            error = ErrorDetail(detail = throwable?.message.toString()),
            data = "nufd"
        )
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?, code: Int): ErrorMessage2 {
        return try {
            val error = gson.fromJson(body?.string(), ErrorBody::class.java)
            error.status = code
            error.toDomain()
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorMessage2(
                status = HttpErrors.BadResponse,
                error = ErrorDetail(detail = e.message.toString())
            )
        }

    }
}


fun convertIoExceptions(throwable: Throwable?): ErrorDetail {

    return if (throwable?.localizedMessage?.contains("Unable to resolve host") == true || throwable?.localizedMessage?.contains(
            "hostname"
        ) == true
    ) {
        ErrorDetail(
            msg = "خطا در برقراری ارتباط با سرور",
            code = "800",
            detail = "لطفا وضعیت اینترنت خود را بررسی  کنید "
        )

    } else if (throwable?.localizedMessage.isNullOrBlank()) {
        ErrorDetail(msg = "خطا در برقراری با سرور", code = "700", detail = "")
    } else {
        ErrorDetail(msg = "${throwable?.localizedMessage}", code = "", detail = "")
    }


}