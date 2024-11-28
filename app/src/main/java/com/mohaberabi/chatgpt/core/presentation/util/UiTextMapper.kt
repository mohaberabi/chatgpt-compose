package com.mohaberabi.chatgpt.core.presentation.util

import com.mohaberabi.chatgpt.core.domain.model.error.DataError


fun DataError.asUiText() = when (this) {
    DataError.CommonError.UNKNOWN -> "Unknown"
    DataError.LocalError.DISK_FULL -> "Disk Full"
    DataError.LocalError.IO_FAILURE -> "Io Failure"
    DataError.LocalError.UNKNOWN -> "Unknown"
    DataError.RemoteError.REQUEST_TIMEOUT -> "Request Timed out "
    DataError.RemoteError.TOO_MANY_REQUESTS -> "Too many Requests"
    DataError.RemoteError.NO_INTERNET -> "No Network"
    DataError.RemoteError.SERVER -> "Server Error"
    DataError.RemoteError.SERIALIZATION -> "Serialization Error"
    DataError.RemoteError.UNKNOWN -> "Unknown Error"
}.let { UiText.Dynamic(it) }