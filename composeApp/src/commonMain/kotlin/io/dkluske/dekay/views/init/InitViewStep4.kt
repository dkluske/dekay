package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.store.model.Settings

@Composable
fun Steppable.InitViewStep4(
    settingsBuilder: SettingsBuilder,
    step4Builder: SettingsBuilder.(Settings.Gender) -> Unit
) {
    val gender = mutableStateOf<Settings.Gender>(Settings.Gender.NOT_DEFINED)
    val onNext: () -> Boolean = {
        settingsBuilder.step4Builder(
            gender.value
        )
        true
    }

    InitDataStepView(
        title = ui.texts.value.initStep4Title,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
}