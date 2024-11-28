package com.mohaberabi.chatgpt.core.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource


sealed interface UiText {


    data object Empty : UiText
    data class Dynamic(val value: String) : UiText

    class StringResource(@StringRes val id: Int, vararg val formatArgs: Any?) : UiText


    @Composable
    fun fold(): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringResource -> stringResource(id = this.id, this.formatArgs)
        }
    }

    fun fold(context: Context): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringResource -> context.getString(this.id, this.formatArgs)
        }

    }

}