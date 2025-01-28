package io.dkluske.dekay.views

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText

@Composable
fun ViewWrapper(
    view: @Composable () -> Unit
) {
    Scaffold(
        topBar = {},
        bottomBar = {
            BottomAppBar {
                Card {
                    CardText(
                        text = "Taskbar here"
                    )
                }
            }
        },
        content = { _ ->
            view()
        }
    )
}