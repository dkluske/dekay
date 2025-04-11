package io.dkluske.dekay.views.init

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder

@Composable
fun Steppable.InitViewStep4(
    settingsBuilder: SettingsBuilder,
    step4Builder: SettingsBuilder.(Settings.Gender) -> Unit
) {
    val gender = mutableStateOf(Settings.Gender.NOT_DEFINED)
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    GenderButton(
                        text = ui.texts.value.male,
                        selected = gender.value == Settings.Gender.MALE,
                    ) {
                        gender.value = Settings.Gender.MALE
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    GenderButton(
                        text = ui.texts.value.female,
                        selected = gender.value == Settings.Gender.FEMALE,
                    ) {
                        gender.value = Settings.Gender.FEMALE
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                GenderButton(
                    text = ui.texts.value.notDefined,
                    selected = gender.value == Settings.Gender.NOT_DEFINED,
                ) {
                    gender.value = Settings.Gender.NOT_DEFINED
                }
            }
        }
    }
}

@Composable
private fun GenderButton(
    text: String,
    selected: Boolean,
    // TODO: icon
    onClick: () -> Unit
) {
    val borderModifier = if (selected) {
        Modifier.border(
            width = 3.dp,
            color = Color(74, 114, 170, 255),
            shape = RoundedCornerShape(8.dp)
        )
    } else {
        Modifier
    }
    Button(
        onClick = onClick,
        modifier = borderModifier.padding(8.dp).fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(60, 60, 60, 200)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    Icons.Filled.Person,
                    "gender",
                    modifier = Modifier.size(50.dp).padding(10.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    style = TextStyle(

                    )
                )
            }
        }
    }
}