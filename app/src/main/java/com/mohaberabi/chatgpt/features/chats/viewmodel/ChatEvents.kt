package com.mohaberabi.chatgpt.features.chats.viewmodel

import com.mohaberabi.chatgpt.core.presentation.util.UiText


sealed interface ChatEvents {

    data class ChatCreated(val chatId: String) : ChatEvents

    data class Error(val error: UiText) : ChatEvents

}