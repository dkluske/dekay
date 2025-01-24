package io.dkluske.dekay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.home.HomeView
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colors = CUSTOM_THEME_DARK
    ) {
        val ui = UI()
        Column(Modifier.fillMaxSize()) {
            when (ui.state.value) {
                is View.Home -> {
                    HomeView(ui = ui)
                }

                is View.Charts -> TODO()
                is View.Habits -> TODO()
                is View.Settings -> TODO()
            }
        }
    }
}