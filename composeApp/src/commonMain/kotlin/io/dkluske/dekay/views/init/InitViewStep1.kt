package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.components.ActionButton

@Composable
fun Steppable.InitViewStep1(
    settingsBuilder: SettingsBuilder,
    step1Builder: SettingsBuilder.(String, String, String?) -> Unit
) {
    val firstName = remember { mutableStateOf<String?>(null) }
    val lastName = remember { mutableStateOf<String?>(null) }
    val nickName = remember { mutableStateOf<String?>(null) }
    val dialogState = remember { mutableStateOf(false) }
    val onNext: () -> Boolean = {
        if (firstName.value == null || lastName.value == null) {
            dialogState.value = true
            false
        } else {
            settingsBuilder.step1Builder(firstName.value!!, lastName.value!!, nickName.value)
            true
        }
    }

    InitDataStepView(
        title = ui.texts.value.initStep1Title,
        onNext = onNext
    ) {
        if (dialogState.value) {
            AlertDialog(
                onDismissRequest = {
                    dialogState.value = false
                },
                confirmButton = {
                    ActionButton(
                        text = ui.texts.value.ok
                    ) {
                        dialogState.value = false
                    }
                },
                title = {
                    Text(text = ui.texts.value.error)
                },
                text = {
                    Text(text = ui.texts.value.pleaseEnterFirstAndLastName)
                }
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(end = 4.dp)
                ) {
                    StepTextInput(
                        placeholder = ui.texts.value.firstName,
                        value = firstName.value ?: ""
                    ) {
                        firstName.value = it
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(start = 4.dp)
                ) {
                    StepTextInput(
                        placeholder = ui.texts.value.lastName,
                        value = lastName.value ?: ""
                    ) {
                        lastName.value = it
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                StepTextInput(
                    placeholder = ui.texts.value.nickName,
                    value = nickName.value ?: ""
                ) {
                    nickName.value = it
                }
            }
        }
    }
}