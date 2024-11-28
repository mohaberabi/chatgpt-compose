package com.mohaberabi.chatgpt.core.domain.model


data class MessageModel(
    val id: String = "",
    val message: String,
    val isMine: Boolean = true,
    val chatId: String = ""
)