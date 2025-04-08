package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.store.model.SettingsBuilder

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {
    val height = mutableStateOf<Int>(170)
    val onNext: () -> Boolean = {
        settingsBuilder.step3Builder(height.value)
        true
    }

    InitDataStepView(
        title = ui.texts.value.initStep3Title,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            // TODO: number formatting
            InputButton(
                displayValue = "${ui.texts.value.steps}",
                onClick = {
                    // TODO: Step goal picker
                }
            )
        }
    }
}