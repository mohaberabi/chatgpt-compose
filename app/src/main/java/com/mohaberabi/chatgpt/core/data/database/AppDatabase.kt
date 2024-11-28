package com.mohaberabi.chatgpt.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohaberabi.chatgpt.core.data.database.dao.ChatDao
import com.mohaberabi.chatgpt.core.data.database.dao.MessageDao
import com.mohaberabi.chatgpt.core.data.database.entity.ChatEntity
import com.mohaberabi.chatgpt.core.data.database.entity.MessageEntity


@Database(
    entities = [
        ChatEntity::class,
        MessageEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun chatDao(): ChatDao
    abstract fun messagesDao(): MessageDao
}