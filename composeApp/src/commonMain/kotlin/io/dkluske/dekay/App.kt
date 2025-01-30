package io.dkluske.dekay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.habits.HabitsView
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
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    IconButton(
                                        onClick = {
                                            ui.state.value = View.Home()
                                        }
                                    ) {
                                        Icon(Icons.Default.Home, "home")
                                    }
                                    IconButton(
                                        onClick = {
                                            ui.state.value = View.Habits()
                                        }
                                    ) {
                                        Icon(Icons.Default.Add, "habits")
                                    }
                                    IconButton(
                                        onClick = {
                                            ui.state.value = View.Charts()
                                        }
                                    ) {
                                        Icon(Icons.Default.Done, "charts")
                                    }
                                    IconButton(
                                        onClick = {
                                            ui.state.value = View.Settings()
                                        }
                                    ) {
                                        Icon(Icons.Default.Settings, "settings")
                                    }
                                }
                            }
                        }
                    },
                    content = {
                        when (ui.state.value) {
                            is View.Home -> HomeView(ui = ui)
                            is View.Charts -> TODO()
                            is View.Habits -> HabitsView(ui = ui)
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
