package com.mohaberabi.chatgpt.core.domain.source.local

import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow


interface ChatLocalDataSource {
    fun getAllChats(): Flow<List<ChatModel>>

    suspend fun createChat(chat: ChatModel)


}