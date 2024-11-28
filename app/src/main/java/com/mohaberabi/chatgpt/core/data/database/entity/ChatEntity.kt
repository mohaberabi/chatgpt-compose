package com.mohaberabi.chatgpt.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = false) val id: String
)