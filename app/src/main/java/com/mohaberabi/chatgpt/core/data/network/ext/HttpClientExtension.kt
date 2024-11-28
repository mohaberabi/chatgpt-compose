package com.mohaberabi.chatgpt.core.data.network.ext

import com.mohaberabi.chatgpt.core.domain.model.error.AppErrorModel
import com.mohaberabi.chatgpt.core.domain.model.error.AppException
import com.mohaberabi.chatgpt.core.domain.model.error.DataError
import com.mohaberabi.chatgpt.core.domain.model.error.toAppError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext


val succuessRange = 200..299


suspend inline fun <reified T> safeRemoteCall(
    block: () -> HttpResponse
): T {

    val response = try {
        block()
    } catch (e: SocketTimeoutException) {
        throw AppException(
            e.toAppError(error = DataError.RemoteError.REQUEST_TIMEOUT)
        )
    } catch (e: UnresolvedAddressException) {
        throw AppException(
            e.toAppError(error = DataError.RemoteError.NO_INTERNET)
        )
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        e.printStackTrace()
        throw AppException(
            e.toAppError(error = DataError.RemoteError.UNKNOWN)
        )
    }
    return mapResponseToData<T>(response)
}

suspend inline fun <reified T> mapResponseToData(
    response: HttpResponse
): T {

    return if (response.status.value in succuessRange) {
        try {
            response.body<T>()
        } catch (e: NoTransformationFoundException) {
            e.printStackTrace()
            throw AppException(
                e.toAppError(
                    error = DataError.RemoteError.SERIALIZATION,
                    code = response.status.value
                )
            )
        }
    } else {
        val error = when (response.status.value) {
            408 -> AppErrorModel(
                error = DataError.RemoteError.REQUEST_TIMEOUT,
                statusCode = 408
            )

            429 -> AppErrorModel(
                error = DataError.RemoteError.TOO_MANY_REQUESTS,
                statusCode = 429
            )

            in 500..599 -> AppErrorModel(
                error = DataError.RemoteError.SERVER,
                statusCode = response.status.value
            )

            else -> AppErrorModel(
                error = DataError.RemoteError.UNKNOWN,
            )
        }
        throw AppException(error)
    }


}