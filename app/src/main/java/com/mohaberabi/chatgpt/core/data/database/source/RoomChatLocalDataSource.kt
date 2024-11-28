package com.mohaberabi.chatgpt.core.data.database.source

import com.mohaberabi.chatgpt.core.data.database.dao.ChatDao
import com.mohaberabi.chatgpt.core.data.database.ext.safeRoomCall
import com.mohaberabi.chatgpt.core.data.database.mapper.toChatEntity
import com.mohaberabi.chatgpt.core.data.database.mapper.toChatModel
import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.source.local.ChatLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomChatLocalDataSource @Inject constructor(

    private val dispatchers: DispatchersProvider,
    private val dao: ChatDao
) : ChatLocalDataSource {
    override fun getAllChats(): Flow<List<ChatModel>> =
        dao.getAllChats().map { list -> list.map { it.toChatModel } }.flowOn(dispatchers.io)

    override suspend fun createChat(chat: ChatModel) {
        withContext(dispatchers.io) {
            safeRoomCall {
                dao.createChat(chat.toChatEntity)
            }
        }
    }
}