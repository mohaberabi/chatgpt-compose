package com.mohaberabi.chatgpt.core.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohaberabi.chatgpt.R


@Composable
fun ChatAppBar(
    modifier: Modifier = Modifier,
    isTyping:Boolean =false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.gpt),
            contentDescription = "gpt",
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.Start,
        ) {

            Text(
                text = stringResource(R.string.chat_gpt),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            if(isTyping){
                Text(
                    text = stringResource(R.string.typing),
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }


        }

    }
}

