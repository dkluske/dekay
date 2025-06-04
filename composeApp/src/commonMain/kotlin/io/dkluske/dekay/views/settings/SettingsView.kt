package io.dkluske.dekay.views.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.util.components.ActionButton
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.WithUI

@Composable
fun WithUI.SettingsView() {
    val settings = ui.database.value.settingsQueries.getSettings().executeAsList().first()
    val resetDialogState = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (resetDialogState.value) {
            AlertDialog(
                onDismissRequest = {
                    resetDialogState.value = false
                },
                confirmButton = {
                    ActionButton(
                        text = ui.texts.value.ok
                    ) {
                        ui.database.value.settingsQueries.deleteSettings()
                        ui.database.value.habitQueries.deleteAll()
                        ui.database.value.habitEntryQueries.deleteAll()
                        ui.health.value.revokeAuthorization()
                        resetDialogState.value = false
                        ui.state.value = View.Init()
                    }
                },
                dismissButton = {
                    ActionButton(
                        text = ui.texts.value.cancel
                    ) {
                        resetDialogState.value = false
                    }
                },
                title = {
                    Text(text = ui.texts.value.warning)
                },
                text = {
                    Text(text = ui.texts.value.resetAppDeletesEverything)
                }
            )
        }
        PaddedMaxWidthRow {
            CardText(text = ui.texts.value.settings, scaleFactor = 1.5f)
        }

        PaddedMaxWidthRow {
            Card {
                SettingsRow(key = ui.texts.value.firstName, value = settings.first_name)
                Divider()
                SettingsRow(key = ui.texts.value.lastName, value = settings.last_name)
                Divider()
                SettingsRow(key = ui.texts.value.nickName, value = settings.nick_name ?: "")
                Divider()
                SettingsRow(
                    key = ui.texts.value.steps,
                    value = settings.daily_step_target.toString()
                )
                Divider()
                SettingsRow(
                    key = ui.texts.value.gender,
                    value = when (Settings.Gender.valueOf(settings.gender)) {
                        Settings.Gender.MALE -> ui.texts.value.male
                        Settings.Gender.FEMALE -> ui.texts.value.female
                        Settings.Gender.NOT_DEFINED -> ui.texts.value.notDefined
                    }
                )
                Divider()
                SettingsRow(key = ui.texts.value.dateOfBirth, value = settings.date_of_birth)
                Divider()
                SettingsRow(key = ui.texts.value.height, value = "${settings.height} cm")
            }
        }
        PaddedMaxWidthRow {
            ActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = ui.texts.value.resetApp,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(255, 65, 65, 255),
                    contentColor = Color.White,
                    disabledContentColor = Color.DarkGray,
                    disabledContainerColor = Color(255, 65, 65, 150)
                )
            ) {
                resetDialogState.value = true
            }
        }
    }
}

@Composable
private fun SettingsRow(
    key: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SettingsRowText(text = key)
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            // TODO: input field for editing?
            SettingsRowText(text = value)
        }
    }
}

@Composable
private fun SettingsRowText(
    text: String
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp
        ),
        color = CUSTOM_THEME_DARK.onPrimary
    )
}