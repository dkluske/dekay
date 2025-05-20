package io.dkluske.dekay.util.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.util.components.ActionButton

@Composable
fun KeyValueCard(
    entries: List<KeyValueCardRowEntry>
) {
    Card {
        entries.forEachIndexed { index, entry ->
            KeyValueCardRow(key = entry.key, value = entry.value, onValueChange = entry.onValueChange)
            if (index < entries.size - 1) {
                Divider()
            }
        }
    }
}

@Composable
private fun KeyValueCardRow(
    key: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            KeyValueCardRowText(text = key)
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            // TODO: input field for editing?
            KeyValueCardRowText(text = value)
        }
    }
}

@Composable
private fun KeyValueCardRowText(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp
        ),
        color = CUSTOM_THEME_DARK.onPrimary
    )
}

data class KeyValueCardRowEntry(
    val key: String,
    val value: String,
    val onValueChange: (String) -> Unit
)