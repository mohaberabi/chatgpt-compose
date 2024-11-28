package com.mohaberabi.chatgpt.core.domain.source.remote

import com.mohaberabi.chatgpt.core.domain.model.MessageModel

interface MessagesRemoteDataSource {
    suspend fun sendMessageAndGetResponse(message: MessageModel): MessageModel
}