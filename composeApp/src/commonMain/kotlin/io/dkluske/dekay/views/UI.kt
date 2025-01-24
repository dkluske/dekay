package io.dkluske.dekay.views

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.dkluske.dekay.util.Configuration

data class UI(
    val state: MutableState<View> = mutableStateOf(View.Home()),
    val configuration: MutableState<Configuration> = mutableStateOf(Configuration())
)