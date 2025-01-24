package io.dkluske.dekay.views.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.ViewWrapper

@Composable
fun HomeView(
    ui: UI
) {
    ViewWrapper {
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Hi ${ui.configuration.value.username}"
            )
        }
    }
}
