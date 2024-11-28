package com.mohaberabi.chatgpt.core.domain.model.error


data class AppErrorModel(
    val message: String? = null,
    val cause: Throwable? = null,
    val error: DataError,
    val statusCode: Int = -1
)

data class AppException(val error: AppErrorModel) : Throwable()


fun Throwable.toAppError(
    error: DataError = DataError.CommonError.UNKNOWN,
    code: Int = -1
) = AppErrorModel(message = message, cause = this, error = error, statusCode = code)