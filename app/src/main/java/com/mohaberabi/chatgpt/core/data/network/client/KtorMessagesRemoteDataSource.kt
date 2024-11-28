package com.mohaberabi.chatgpt.core.data.network.client

import com.mohaberabi.chatgpt.core.data.network.NetowrkConst
import com.mohaberabi.chatgpt.core.data.network.dto.OpenAIResponse
import com.mohaberabi.chatgpt.core.data.network.dto.OpenAiMessage
import com.mohaberabi.chatgpt.core.data.network.dto.OpenAiRequest
import com.mohaberabi.chatgpt.core.data.network.ext.safeRemoteCall
import com.mohaberabi.chatgpt.core.domain.model.DefaultDispatchersProvider
import com.mohaberabi.chatgpt.core.domain.model.DispatchersProvider
import com.mohaberabi.chatgpt.core.domain.model.MessageModel
import com.mohaberabi.chatgpt.core.domain.source.remote.MessagesRemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KtorMessagesRemoteDataSource @Inject constructor(
    private val client: HttpClient,
    private val dispatchers: DispatchersProvider
) : MessagesRemoteDataSource {
    override suspend fun sendMessageAndGetResponse(
        message: MessageModel,
    ): MessageModel {
        val request = OpenAiRequest(
            messages = listOf(OpenAiMessage(role = "user", message.message)),
            model = "gpt-3.5-turbo",
        )
        return withContext(dispatchers.io) {
            val response = safeRemoteCall<OpenAIResponse> {
                client.post(
                    urlString = NetowrkConst.BASE_URL,
                ) {
                    setBody(request)
                }
            }
            message.copy(
                isMine = false,
                message = response.choices.firstOrNull()?.message?.content
                    ?: "Could Not Find An Answer"
            )

        }

    }

}