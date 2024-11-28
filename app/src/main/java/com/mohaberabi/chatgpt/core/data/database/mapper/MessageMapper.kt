package com.mohaberabi.chatgpt.core.data.database.mapper

import com.mohaberabi.chatgpt.core.data.database.entity.ChatEntity
import com.mohaberabi.chatgpt.core.data.database.entity.MessageEntity
import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import com.mohaberabi.chatgpt.core.domain.model.MessageModel


val MessageEntity.messageModel
    get() = MessageModel(
        isMine = isMine,
        chatId = chatId,
        id = id,
        message = message
    )
val MessageModel.messageEntity
    get() = MessageEntity(
        isMine = isMine,
        chatId = chatId,
        id = id,
        message = message
    )