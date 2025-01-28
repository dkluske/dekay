package io.dkluske.dekay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
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

        BoxWithConstraints(
            modifier = Modifier.background(CUSTOM_THEME_DARK.background)
        ) {
            Column(Modifier.fillMaxSize()) {
                Scaffold(
                    bottomBar = {
                        BottomAppBar(
                            containerColor = Color.Transparent
                        ) {
                            // TODO: add real taskbar content
                            Card {
                                CardText(
                                    text = "Taskbar here"
                                )
                            }
                        }
                    },
                    content = {
                        when (ui.state.value) {
                            is View.Home -> {
                                HomeView(ui = ui)
                            }

                            is View.Charts -> TODO()
                            is View.Habits -> TODO()
                            is View.Settings -> TODO()
                            is View.Init -> TODO()
                        }
                    },
                    backgroundColor = CUSTOM_THEME_DARK.background
                )
            }
        }

    }
}
