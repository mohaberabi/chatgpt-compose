package com.mohaberabi.chatgpt.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohaberabi.chatgpt.ui.theme.ChatGptTheme


@Composable
fun MessageBubble(
    modifier: Modifier = Modifier,
    text: String,
    isMine: Boolean
) {
    val bubbleColor = if (isMine) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = if (isMine) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = if (isMine) 16.dp else 0.dp,
                        topEnd = if (isMine) 0.dp else 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp
                    ),
                )
                .background(bubbleColor)
                .padding(12.dp)
                .widthIn(max = 300.dp)

        ) {
            Text(
                text = text,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewChatBubbble() {
    ChatGptTheme {


        MessageBubble(text = "Hello what is compose ....?", isMine = false)
    }
}