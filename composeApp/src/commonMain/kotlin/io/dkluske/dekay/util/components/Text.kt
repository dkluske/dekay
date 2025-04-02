package io.dkluske.dekay.util.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.util.CUSTOM_THEME_DARK

@Composable
fun CardText(
    text: String,
    scaleFactor: Float = 1f,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = CUSTOM_THEME_DARK.onPrimary,
        style = TextStyle(
            fontSize = (18 * scaleFactor).sp
        ),
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(16.dp)
    )
}
