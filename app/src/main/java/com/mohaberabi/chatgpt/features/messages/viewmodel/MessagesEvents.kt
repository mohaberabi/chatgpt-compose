package com.mohaberabi.chatgpt.features.messages.viewmodel

import com.mohaberabi.chatgpt.core.presentation.util.UiText

sealed interface MessagesEvents {


    data class Error(val error: UiText) : MessagesEvents

}