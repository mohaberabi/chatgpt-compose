package com.mohaberabi.chatgpt.core.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class OpenAIResponse(
    val id: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
)

@Serializable
data class Choice(
    val index: Int,
    val message: Message,
)


@Serializable
data class Message(
    val content: String
)