package com.mohaberabi.chatgpt.core.domain.model

import com.mohaberabi.chatgpt.core.domain.model.error.AppError
import com.mohaberabi.chatgpt.core.domain.model.error.AppErrorModel
import com.mohaberabi.chatgpt.core.domain.model.error.AppException


sealed interface AppResult<out T> {
    data class Done<T>(val data: T) : AppResult<T>
    data class Error(val error: AppErrorModel) : AppResult<Nothing>
}

inline fun <reified T> AppResult<T>.onError(
    block: (AppErrorModel) -> Unit
): AppResult<T> {

    if (this is AppResult.Error) {
        block(this.error)
    }
    return this
}

inline fun <reified T> AppResult<T>.onDone(
    block: (T) -> Unit
): AppResult<T> {

    if (this is AppResult.Done) {
        block(this.data)
    }
    return this
}

inline fun <T, R> AppResult<T>.map(map: (T) -> R): AppResult<R> {
    return when (this) {
        is AppResult.Error -> AppResult.Error(error)
        is AppResult.Done -> AppResult.Done(map(data))
    }
}


inline fun <reified T> handleAppResult(
    block: () -> T
): AppResult<T> {
    return try {
        AppResult.Done(block())
    } catch (e: AppException) {
        AppResult.Error(e.error)
    }
}