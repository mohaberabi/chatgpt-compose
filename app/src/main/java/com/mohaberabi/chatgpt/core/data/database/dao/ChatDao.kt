package com.mohaberabi.chatgpt.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.chatgpt.core.data.database.entity.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {


    @Upsert
    suspend fun createChat(entity: ChatEntity)


    @Query("SELECT * FROM chat")
    fun getAllChats(): Flow<List<ChatEntity>>
}