package com.mohaberabi.chatgpt.core.data.database.source

import com.mohaberabi.chatgpt.core.data.database.dao.ChatDao
import com.mohaberabi.chatgpt.core.data.database.dao.MessageDao
import com.mohaberabi.chatgpt.core.data.database.ext.safeRoomCall
import com.mohaberabi.chatgpt.core.data.database.mapper.messageEntity
import com.mohaberabi.chatgpt.core.data.database.mapper.messageModel
import com.mohaberabi.chatgpt.core.data.database.mapper.toChatEntity
import com.mohaberabi.chatgpt.core.data.database.mapper.toChatModel
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import com.mohaberabi.chatgpt.core.domain.source.local.MessagesLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomMessagesLocalDataSource @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val dao: MessageDao
) : MessagesLocalDataSource {
    override suspend fun saveMessage(message: MessageModel) {
        withContext(dispatchers.io) {
            safeRoomCall {
                dao.insertMessage(message.messageEntity)
            }
        }
    }


    override fun getChatMessages(chatId: String): Flow<List<MessageModel>> =
        dao.getAllChatMessages(chatId).map { list -> list.map { it.messageModel } }
            .flowOn(dispatchers.io)

}