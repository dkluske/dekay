package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.components.ActionButton

@Composable
fun Steppable.InitWrapperStepView(
    title: String?,
    text: String?,
    nextButtonText: String,
    block: @Composable Steppable.() -> Unit
) {
    InitViewBase {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                title?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        StepTitle(it)
                    }
                }
                text?.let {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        StepText(it)
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            block()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(
                text = nextButtonText,
                onClick = {
                    next()
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}