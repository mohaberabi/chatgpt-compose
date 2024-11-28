package com.mohaberabi.chatgpt.core.data.repository

import com.mohaberabi.chatgpt.core.domain.model.AppResult
import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import com.mohaberabi.chatgpt.core.domain.model.handleAppResult
import com.mohaberabi.chatgpt.core.domain.repository.ChatId
import com.mohaberabi.chatgpt.core.domain.repository.ChatRepository
import com.mohaberabi.chatgpt.core.domain.source.local.ChatLocalDataSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject


class OfflineFirstChatRepository @Inject constructor(
    private val chatLocalDataSource: ChatLocalDataSource,
) : ChatRepository {
    override suspend fun createChat(): AppResult<ChatId> {
        return handleAppResult {
            val randomId = UUID.randomUUID().toString()
            val chat = ChatModel(randomId)
            chatLocalDataSource.createChat(chat)
            chat.id
        }
    }

    override fun getAllChats(): Flow<List<ChatModel>> = chatLocalDataSource.getAllChats()
}