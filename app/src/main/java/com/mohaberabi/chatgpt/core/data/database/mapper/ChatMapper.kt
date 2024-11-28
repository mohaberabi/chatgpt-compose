package com.mohaberabi.chatgpt.core.data.database.mapper

import com.mohaberabi.chatgpt.core.data.database.entity.ChatEntity
import com.mohaberabi.chatgpt.core.domain.model.ChatModel

val ChatEntity.toChatModel get() = ChatModel(id)
val ChatModel.toChatEntity get() = ChatEntity(id)