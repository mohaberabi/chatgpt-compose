package com.mohaberabi.chatgpt.core.presentation.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface SnackBarController {
    suspend fun show(message: String)
    val messages: Flow<String>
}


class DefaultSnackBarController : SnackBarController {
    private val channel = Channel<String>()
    override suspend fun show(message: String) {
        channel.send(message)
    }

    override val messages: Flow<String>
        get() = channel.receiveAsFlow()

}