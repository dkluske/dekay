package io.dkluske.dekay.views

import androidx.compose.runtime.Composable

@Composable
fun ViewWrapper(
    view: @Composable () -> Unit
) {
    view()
}