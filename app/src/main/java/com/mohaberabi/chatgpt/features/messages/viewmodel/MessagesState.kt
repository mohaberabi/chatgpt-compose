package com.mohaberabi.chatgpt.features.messages.viewmodel

import androidx.compose.runtime.Stable
import com.mohaberabi.chatgpt.core.domain.model.MessageModel


@Stable
data class MessagesState(
    val messages: List<MessageModel> = listOf(),
    val messageText: String = "",
    val typing: Boolean = false,
    val loading: Boolean = false
)