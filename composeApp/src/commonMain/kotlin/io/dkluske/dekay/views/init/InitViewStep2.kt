package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.store.model.SettingsBuilder
import kotlinx.datetime.LocalDate

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate) -> Unit
) {
    val dateOfBirth = mutableStateOf(LocalDate(2000, 1, 1))
    val onNext: () -> Boolean = {
        // TODO: remove default values and add validation
        settingsBuilder.step2Builder(
            dateOfBirth.value
        )
        true
    }

    InitDataStepView(
        title = ui.texts.value.initStep2Title,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            // TODO: Localized Date formatter
            InputButton(
                displayValue = dateOfBirth.value.toString(),
                onClick = {
                    // TODO: Date picker
                }
            )
        }
    }
}