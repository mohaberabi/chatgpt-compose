package com.mohaberabi.chatgpt.core.domain.usecase

import com.mohaberabi.chatgpt.core.domain.repository.MessagesRepository
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    operator fun invoke(chat: String) = messagesRepository.getChatMessages(chat)
}