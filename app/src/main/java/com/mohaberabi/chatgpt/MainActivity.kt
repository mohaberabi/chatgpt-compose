package com.mohaberabi.chatgpt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mohaberabi.chatgpt.core.presentation.compose.EventCollector
import com.mohaberabi.chatgpt.core.presentation.navigation.AppNavHost
import com.mohaberabi.chatgpt.core.presentation.util.DefaultSnackBarController
import com.mohaberabi.chatgpt.ui.theme.ChatGptTheme
import dagger.hilt.android.AndroidEntryPoint


val LocalSnackBarController = compositionLocalOf { DefaultSnackBarController() }

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalSnackBarController provides DefaultSnackBarController(),
            ) {

                val snackBarController = LocalSnackBarController.current
                val hostState = remember {
                    SnackbarHostState()
                }
                EventCollector(
                    flow = snackBarController.messages,
                ) { message ->
                    hostState.showSnackbar(message)
                }
                ChatGptTheme {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = hostState)
                        },
                    ) {
                        AppNavHost()
                    }
                }
            }
        }
    }
}
