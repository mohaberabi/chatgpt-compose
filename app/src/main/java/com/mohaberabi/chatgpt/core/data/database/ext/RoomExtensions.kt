package com.mohaberabi.chatgpt.core.data.database.ext

import com.mohaberabi.chatgpt.core.data.network.ext.mapResponseToData
import com.mohaberabi.chatgpt.core.domain.model.error.AppException
import com.mohaberabi.chatgpt.core.domain.model.error.DataError
import com.mohaberabi.chatgpt.core.domain.model.error.toAppError
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.ensureActive
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> safeRoomCall(
    block: () -> T
): T {
    return try {
        block()
    } catch (e: IOException) {
        throw AppException(e.toAppError(error = DataError.LocalError.IO_FAILURE))
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        e.printStackTrace()
        throw AppException(e.toAppError(error = DataError.LocalError.UNKNOWN))
    }

}