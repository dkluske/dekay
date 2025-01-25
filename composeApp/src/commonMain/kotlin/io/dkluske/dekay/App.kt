package io.dkluske.dekay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        val init = false // TODO: check if config
        val initialView = mutableStateOf(
            if (init) {
                View.Init()
            } else {
                View.Home()
            }
        )
        val ui = remember { UI(state = initialView) }
        Column(Modifier.fillMaxSize()) {
            when (ui.state.value) {
                is View.Home -> {
                    HomeView(ui = ui)
                }

                is View.Charts -> TODO()
                is View.Habits -> TODO()
                is View.Settings -> TODO()
                is View.Init -> TODO()
            }
        }
    }
}
