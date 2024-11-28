package com.mohaberabi.chatgpt.core.domain.usecase

import com.mohaberabi.chatgpt.core.domain.repository.ChatRepository
import javax.inject.Inject

class CreateChatUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke() = chatRepository.createChat()
}