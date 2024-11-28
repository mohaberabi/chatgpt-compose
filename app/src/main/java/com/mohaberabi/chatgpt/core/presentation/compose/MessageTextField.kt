package com.mohaberabi.chatgpt.core.presentation.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohaberabi.chatgpt.ui.theme.ChatGptTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    onSendMessage: () -> Unit = {},
    messageText: String = ""
) {


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {


            TextField(
                singleLine = false,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier
                    .border(
                        BorderStroke(0.5.dp, Color.Gray),
                        shape = RoundedCornerShape(22.dp)
                    )
                    .weight(1f)
                    .wrapContentHeight()
//                    .heightIn(max = 36.dp)
                    .padding(end = 8.dp),
                placeholder = {
                    Text(
                        text = "Type a message..",
                        lineHeight = 4.sp,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                    )
                },
                value = messageText,
                textStyle = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                ),
                onValueChange = onTextChanged,
            )
            Spacer(modifier = Modifier.width(4.dp))

            if(messageText.isNotEmpty()){
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { onSendMessage() }
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Send,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(8.dp),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Send"
                    )
                }
            }

        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun PreviewMessageTextField() {
    ChatGptTheme {
        MessageTextField(messageText = "hey i love kotlin it is my best programming language ever ")
    }
}