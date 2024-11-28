package com.mohaberabi.chatgpt.features.chats.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.chatgpt.core.domain.model.onDone
import com.mohaberabi.chatgpt.core.domain.model.onError
import com.mohaberabi.chatgpt.core.domain.usecase.CreateChatUseCase
import com.mohaberabi.chatgpt.core.domain.usecase.GetChatsUseCase
import com.mohaberabi.chatgpt.core.presentation.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val createChatUseCase: CreateChatUseCase
) : ViewModel() {
    val chats = getChatsUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        listOf()
    )


    private val _events = Channel<ChatEvents>()
    val events = _events.receiveAsFlow()

    fun createChat() {
        viewModelScope.launch {
            createChatUseCase()
                .onError { _events.send(ChatEvents.Error(UiText.Empty)) }
                .onDone { _events.send(ChatEvents.ChatCreated(it)) }
        }
    }

}