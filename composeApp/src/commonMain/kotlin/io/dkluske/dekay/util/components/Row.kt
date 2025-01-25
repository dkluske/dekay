package io.dkluske.dekay.util.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PaddedMaxWidthRow(
    modifier: Modifier = Modifier,
    padding: Int = 8,
    content: @Composable () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth().padding(padding.dp)) {
        content()
    }
}