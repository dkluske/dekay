package io.dkluske.dekay.util.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.util.CUSTOM_THEME_DARK

@Composable
fun CardText(
    text: String
) {
    Text(
        text = text,
        color = CUSTOM_THEME_DARK.onPrimary,
        style = TextStyle(
            fontSize = 25.sp
        ),
        modifier = Modifier.padding(16.dp)
    )
}