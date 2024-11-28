package com.mohaberabi.chatgpt.core.data.repository

import com.mohaberabi.chatgpt.core.domain.model.AppResult
import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import com.mohaberabi.chatgpt.core.domain.model.handleAppResult
import com.mohaberabi.chatgpt.core.domain.repository.MessagesRepository
import com.mohaberabi.chatgpt.core.domain.source.local.MessagesLocalDataSource
import com.mohaberabi.chatgpt.core.domain.source.remote.MessagesRemoteDataSource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class OfflineFirstMessagesRepository @Inject constructor(
    private val messagesRemoteDataSource: MessagesRemoteDataSource,
    private val messagesLocalDataSource: MessagesLocalDataSource,
) : MessagesRepository {
    override suspend fun sendMessage(chatId: String, message: String): AppResult<Unit> {
        return handleAppResult {
            val id = UUID.randomUUID().toString()
            val mine = MessageModel(message = message, chatId = chatId, isMine = true, id = id)
            val other = messagesRemoteDataSource.sendMessageAndGetResponse(message = mine)
            coroutineScope {
                joinAll(
                    launch { messagesLocalDataSource.saveMessage(mine) },
                    launch { messagesLocalDataSource.saveMessage(other.copy(id = "${id}_CHATGPT")) },
                )
            }
        }
    }

    override fun getChatMessages(chatId: String): Flow<List<MessageModel>> =
        messagesLocalDataSource.getChatMessages(chatId)
}