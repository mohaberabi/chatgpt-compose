package com.mohaberabi.chatgpt.core.domain.repository

import com.mohaberabi.chatgpt.core.domain.model.AppResult
import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow


typealias ChatId = String

interface ChatRepository {


    suspend fun createChat(): AppResult<ChatId>


    fun getAllChats(): Flow<List<ChatModel>>
}