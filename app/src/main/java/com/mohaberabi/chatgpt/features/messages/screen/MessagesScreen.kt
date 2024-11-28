package com.mohaberabi.chatgpt.features.messages.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.chatgpt.LocalSnackBarController
import com.mohaberabi.chatgpt.R
import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import com.mohaberabi.chatgpt.core.presentation.compose.ChatAppBar
import com.mohaberabi.chatgpt.core.presentation.compose.EventCollector
import com.mohaberabi.chatgpt.core.presentation.compose.MessageBubble
import com.mohaberabi.chatgpt.core.presentation.compose.MessageTextField
import com.mohaberabi.chatgpt.features.messages.viewmodel.MessagesEvents
import com.mohaberabi.chatgpt.features.messages.viewmodel.MessagesState
import com.mohaberabi.chatgpt.features.messages.viewmodel.MessagesViewModel
import com.mohaberabi.chatgpt.ui.theme.ChatGptTheme


@Composable
fun MessagesScreenRoot(
    onGoBack: () -> Unit,
    viewmodel: MessagesViewModel = hiltViewModel()
) {


    val snackBarController = LocalSnackBarController.current
    val context = LocalContext.current
    EventCollector(flow = viewmodel.events) { event ->
        when (event) {
            is MessagesEvents.Error -> snackBarController.show(message = event.error.fold(context))
        }
    }
    val state by viewmodel.state.collectAsStateWithLifecycle()
    MessagesScreen(
        state = state,
        onGoBack = onGoBack,
        onSendMessage = viewmodel::sendMessage,
        onTextChanged = viewmodel::messageTxtChanged
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    onSendMessage: () -> Unit = {},
    state: MessagesState,
    onGoBack: () -> Unit = {}
) {


    val scrollState = rememberLazyListState()
    LaunchedEffect(key1 = state.messages) {
        if (state.messages.isNotEmpty()) {
            scrollState.scrollToItem(state.messages.lastIndex)
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ChatAppBar(isTyping = state.typing)
                },
                navigationIcon = {
                    IconButton(
                        onClick = onGoBack,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }

                },
            )
        }
    ) { padding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {


            LazyColumn(
                state = scrollState,
                modifier = Modifier.weight(1f),
            ) {

                items(
                    state.messages,
                ) { message ->
                    MessageBubble(
                        text = message.message,
                        isMine = message.isMine
                    )
                }
            }
            MessageTextField(
                onTextChanged = onTextChanged,
                onSendMessage = onSendMessage,
                messageText = state.messageText,
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewMessagsScreen() {
    ChatGptTheme {


        MessagesScreen(
            state = MessagesState(
                messages = listOf(
                    MessageModel(
                        "",
                        "hey chat , i love kotlin , it is amazing powerfull consise programming language man "
                    ),
                    MessageModel("", "hey chat", isMine = false),
                    MessageModel("", "hey chat"),
                    MessageModel("", "hey chat"),
                    MessageModel("", "hey chat", isMine = false),
                    MessageModel("", "hey chat", isMine = false),
                )
            )
        )
    }
}