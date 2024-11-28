package com.mohaberabi.chatgpt.core.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OpenAiRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<OpenAiMessage>,
    @SerialName("max_tokens") val maxTokens: Int = 150,
    val temperature: Double = 0.7,
    @SerialName("top_p") val topP: Double = 1.0,
    @SerialName("frequency_penalty") val frequencyPenalty: Double = 0.0,
    @SerialName("presence_penalty") val presencePenalty: Double = 0.0
)

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String
)