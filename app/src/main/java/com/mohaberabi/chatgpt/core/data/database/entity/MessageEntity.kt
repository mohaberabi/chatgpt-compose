package com.mohaberabi.chatgpt.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val message: String,
    val isMine: Boolean = true,
    val chatId: String
)