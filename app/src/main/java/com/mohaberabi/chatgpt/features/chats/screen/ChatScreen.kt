package com.mohaberabi.chatgpt.features.chats.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.chatgpt.LocalSnackBarController
import com.mohaberabi.chatgpt.R
import com.mohaberabi.chatgpt.core.domain.model.ChatModel
import com.mohaberabi.chatgpt.core.domain.repository.ChatId
import com.mohaberabi.chatgpt.core.presentation.compose.EventCollector
import com.mohaberabi.chatgpt.features.chats.viewmodel.ChatEvents
import com.mohaberabi.chatgpt.features.chats.viewmodel.ChatViewModel
import com.mohaberabi.chatgpt.ui.theme.ChatGptTheme


@Composable
fun ChatScreenRoot(
    onGoMessages: (ChatId) -> Unit,
    viewmodel: ChatViewModel = hiltViewModel()
) {

    val snackBarController = LocalSnackBarController.current
    val chats by viewmodel.chats.collectAsStateWithLifecycle()

    EventCollector(
        viewmodel.events,
    ) { event ->
        when (event) {
            is ChatEvents.ChatCreated -> onGoMessages(event.chatId)
            is ChatEvents.Error -> snackBarController.show("error ")
        }
    }
    ChatScreen(
        onCreateChat = viewmodel::createChat,
        chats = chats,
        onChatClick = onGoMessages
    )
}

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    onCreateChat: () -> Unit = {},
    onChatClick: (ChatId) -> Unit = {},
    chats: List<ChatModel> = listOf()
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateChat,
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.create_chat)
                )
            }
        }
    ) {


            padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.chats),
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
            )
            LazyColumn(

            ) {
                items(chats) { chat ->


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onChatClick(chat.id) }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(42.dp),
                            painter = painterResource(id = R.drawable.gpt),
                            contentDescription = stringResource(id = R.string.gpt)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Chat ${chat.id}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

        }

    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun PreviewChatScreen() {
    ChatGptTheme {
        ChatScreen(
            chats = listOf(
                ChatModel("13218398123"),
                ChatModel("13218398123"),
                ChatModel("13218398123"),
                ChatModel("13218398123"),
            ),
        )
    }
}