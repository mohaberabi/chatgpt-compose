package com.mohaberabi.chatgpt.core.domain.usecase

import com.mohaberabi.chatgpt.core.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {


    operator fun invoke() = chatRepository.getAllChats()
}