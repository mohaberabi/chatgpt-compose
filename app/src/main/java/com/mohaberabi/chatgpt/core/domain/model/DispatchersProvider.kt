package com.mohaberabi.chatgpt.core.domain.model

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersProvider {

    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

    val io: CoroutineDispatcher
}


class DefaultDispatchersProvider @Inject constructor(
) : DispatchersProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main


    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}