package com.mohaberabi.chatgpt.core.domain.repository

import com.mohaberabi.chatgpt.core.domain.model.AppResult
import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {


    suspend fun sendMessage(chatId: String, message: String): AppResult<Unit>
    fun getChatMessages(chatId: String): Flow<List<MessageModel>>
}