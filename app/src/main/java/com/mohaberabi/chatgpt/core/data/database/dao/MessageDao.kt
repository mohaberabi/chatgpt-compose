package com.mohaberabi.chatgpt.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.chatgpt.core.data.database.entity.MessageEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {


    @Upsert
    suspend fun insertMessage(message: MessageEntity)


    @Query("SELECT * FROM message WHERE chatId= :chatId")
    fun getAllChatMessages(chatId: String): Flow<List<MessageEntity>>
}