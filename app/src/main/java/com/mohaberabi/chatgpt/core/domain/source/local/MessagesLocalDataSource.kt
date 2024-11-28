package com.mohaberabi.chatgpt.core.domain.source.local

import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessagesLocalDataSource {


    suspend fun saveMessage(message: MessageModel)
    fun getChatMessages(chatId: String): Flow<List<MessageModel>>
}