package com.mohaberabi.chatgpt.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.chatgpt.features.chats.screen.ChatScreenRoot
import com.mohaberabi.chatgpt.features.messages.screen.MessagesScreenRoot
import kotlinx.serialization.Serializable


@Serializable
data object ChatRoute

@Serializable
data class MessagesRoute(val chatId: String)

@Composable
fun AppNavHost(
    controller: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = controller,
        startDestination = ChatRoute
    ) {
        composable<ChatRoute> {
            ChatScreenRoot(
                onGoMessages = controller::goToMessages,
            )
        }
        composable<MessagesRoute> { MessagesScreenRoot(onGoBack = { controller.popBackStack() }) }

    }
}


private fun NavController.goToMessages(id: String) = navigate(MessagesRoute(id))