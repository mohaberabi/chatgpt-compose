package com.mohaberabi.chatgpt.core.domain.usecase

import com.mohaberabi.chatgpt.core.domain.repository.MessagesRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    suspend operator fun invoke(chatId: String, message: String) =
        messagesRepository.sendMessage(chatId, message)
}