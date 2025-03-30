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
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import kotlinx.datetime.LocalDate

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit
) {
    val dateOfBirth = mutableStateOf<LocalDate?>(null)
    val height = mutableStateOf<Int?>(null)
    val gender = mutableStateOf(Settings.Gender.NOT_DEFINED)
    val onNext: () -> Boolean = {
        // TODO: remove default values and add validation
        settingsBuilder.step2Builder(
            dateOfBirth.value ?: LocalDate(1970, 1, 1),
            height.value ?: 180,
            gender.value
        )
        true
    }

    InitDataStepView(
        title = "Step 2: Health Information",
        text = "Please enter your health information to get started.",
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
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(end = 4.dp)
                ) {
                    // TODO: Localized Date formatter
                    InputButton(
                        displayValue = dateOfBirth.value?.toString() ?: "Date of Birth",
                        onClick = {
                            // TODO: Date picker
                        }
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(start = 4.dp)
                ) {
                    InputButton(
                        displayValue = height.value?.toString() ?: "Height",
                        onClick = {
                            // TODO: Height picker
                        }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputButton(
                    displayValue = when (gender.value) {
                        Settings.Gender.MALE -> "Male"
                        Settings.Gender.FEMALE -> "Female"
                        Settings.Gender.NOT_DEFINED -> "Not defined"
                    },
                    onClick = {
                        // TODO: Gender Picker (selection)
                    }
                )
            }
        }
    }
}