package com.mohaberabi.chatgpt.features.messages.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.mohaberabi.chatgpt.core.domain.model.onDone
import com.mohaberabi.chatgpt.core.domain.model.onError
import com.mohaberabi.chatgpt.core.domain.usecase.GetMessagesUseCase
import com.mohaberabi.chatgpt.core.domain.usecase.SendMessageUseCase
import com.mohaberabi.chatgpt.core.presentation.navigation.MessagesRoute
import com.mohaberabi.chatgpt.core.presentation.util.UiText
import com.mohaberabi.chatgpt.core.presentation.util.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val getMessages: GetMessagesUseCase,
    private val sendMessageAndGetResponse: SendMessageUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val chatId = savedStateHandle.toRoute<MessagesRoute>().chatId
    private val _state = MutableStateFlow(MessagesState())
    val state = _state.asStateFlow()
    private val messagesJob = getMessages(chatId)
        .onStart { _state.update { it.copy(loading = true) } }
        .onEach { mssgs -> _state.update { it.copy(messages = mssgs, loading = false) } }
        .launchIn(viewModelScope)
    private var sendMessageJob: Job? = null

    private val _events = Channel<MessagesEvents>()
    val events = _events.receiveAsFlow()
    fun messageTxtChanged(value: String) = _state.update { it.copy(messageText = value) }
    fun sendMessage() {
        sendMessageJob?.cancel()
        sendMessageJob = viewModelScope.launch {
            _state.update { it.copy(typing = true) }
            val txt = _state.value.messageText
            sendMessageAndGetResponse(chatId = chatId, message = txt)
                .onError { error ->
                    _events.send(MessagesEvents.Error(error.error.asUiText()))
                    _state.update { it.copy(typing = true) }
                }.onDone { _state.update { it.copy(messageText = "", typing = false) } }

        }

    }

}