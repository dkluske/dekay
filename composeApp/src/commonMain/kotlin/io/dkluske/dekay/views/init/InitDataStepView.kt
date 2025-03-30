package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.components.ActionButton

@Composable
fun Steppable.InitDataStepView(
    title: String,
    text: String,
    onNext: () -> Boolean,
    block: @Composable Steppable.() -> Unit
) {
    InitViewBase {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            StepTitle(title)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            StepText(text)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            block()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .padding(end = 4.dp)
            ) {
                ActionButton(
                    text = "Back",
                    onClick = {
                        previous()
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 4.dp)
            ) {
                ActionButton(
                    text = "Next",
                    onClick = {
                        if (onNext()) {
                            next()
                        }
                    }
                )
            }
        }
    }
}