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

@Composable
fun Steppable.InitViewStep5(
    settingsBuilder: SettingsBuilder,
    step5Builder: SettingsBuilder.(Int) -> Unit
) {
    val stepGoal = mutableStateOf(10000)
    val onNext: () -> Boolean = {
        settingsBuilder.step5Builder(stepGoal.value)
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
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputButton(
                    displayValue = "${stepGoal.value} ${ui.texts.value.steps}",
                    onClick = {
                        // TODO: Gender Picker (selection)
                    }
                )
            }
        }
    }
}