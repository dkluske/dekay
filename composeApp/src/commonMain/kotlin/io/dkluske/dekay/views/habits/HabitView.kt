@file:OptIn(ExperimentalUuidApi::class)

package io.dkluske.dekay.views.habits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.Weekday
import io.dkluske.dekay.util.components.AddButton
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.util.components.TextInput
import io.dkluske.dekay.views.UI
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Habit(
    val id: Uuid,
    val title: String,
    val targetHabitDays: List<Weekday>,
    val checkedWeekdays: List<Weekday>
)


@OptIn(ExperimentalUuidApi::class)
@Composable
fun HabitsView(
    ui: UI
) {
    val modalBottomSheet = AddHabitModalBottomSheet(
        isShown = remember { mutableStateOf(false) }
    )
    LazyColumn {
        item {
            PaddedMaxWidthRow(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardText(
                    text = "Habits",
                    scaleFactor = 1.5f
                )
                AddButton {
                    modalBottomSheet.show()
                }
            }
        }

        // TODO: remove when db is available
        val mockupData = listOf(
            Habit(
                id = Uuid.random(),
                title = "10.000 Steps",
                targetHabitDays = listOf(
                    Weekday.MONDAY,
                    Weekday.WEDNESDAY,
                    Weekday.FRIDAY
                ),
                checkedWeekdays = listOf(
                    Weekday.TUESDAY
                )
            )
        )

        item {
            PaddedMaxWidthRow {
                Column {
                    mockupData.forEach { habit ->
                        Card {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(4f)
                                ) {
                                    Row {
                                        CardText(habit.title)
                                    }
                                    Row {
                                        HabitDays(
                                            checkedOnes = habit.checkedWeekdays
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Row {
                                        IconButton(
                                            onClick = {
                                                // TODO: set checked
                                            }
                                        ) {
                                            Icon(Icons.Default.Done, "done")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    HabitBottomSheet(
        modal = modalBottomSheet,
        ui = ui
    )
}

@Composable
private fun HabitDays(
    checkedOnes: List<Weekday>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Weekday.entries.toTypedArray().forEach { day ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        Color.LightGray,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${day.initial}",
                    modifier = Modifier.padding(4.dp),
                    color = if (checkedOnes.contains(day)) {
                        Color.Green
                    } else Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HabitBottomSheet(
    modal: AddHabitModalBottomSheet,
    ui: UI
) {
    val sheetState = rememberModalBottomSheetState()

    LaunchedEffect(modal.isShown.value) {
        if (modal.isShown.value) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    // TODO: remove when fixed
    if (sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                modal.hide()
            },
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            containerColor = CUSTOM_THEME_DARK.background,
            content = {
                val titleInput = remember { mutableStateOf(TextFieldValue()) }
                val descriptionInput = remember { mutableStateOf(TextFieldValue()) }
                PaddedMaxWidthRow {
                    CardText(
                        text = ui.texts.value.habit
                    )
                }
                PaddedMaxWidthRow {
                    TextInput(
                        value = titleInput.value.text,
                        onValueChange = {
                            titleInput.value = TextFieldValue(it)
                        },
                        placeholder = ui.texts.value.title
                    )
                }
                PaddedMaxWidthRow {
                    TextInput(
                        value = descriptionInput.value.text,
                        onValueChange = {
                            descriptionInput.value = TextFieldValue(it)
                        },
                        placeholder = ui.texts.value.description,
                        minLines = 3
                    )
                }
                PaddedMaxWidthRow(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    AddHabitModalBottomSheetButton(
                        color = Color(255, 81, 81, 255),
                        text = ui.texts.value.cancel
                    ) {
                        modal.hide()
                    }
                    AddHabitModalBottomSheetButton(
                        color = Color(86, 136, 255, 255),
                        text = ui.texts.value.add
                    ) {
                        val id = Uuid.random()
                        val sigBits = id.toLongs { most, least -> most to least }
                        ui.database.value.habitQueries.insert(
                            io.dkluske.dekay.database.Habit(
                                id_mostSigBits = sigBits.first,
                                id_leastSigBits = sigBits.second,
                                title = titleInput.value.text,
                                description = descriptionInput.value.text
                            )
                        )
                        modal.hide()
                    }
                }
            },
        )
    }
}

@Composable
private fun AddHabitModalBottomSheetButton(
    color: Color,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = color
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        CardText(
            text = text,
            scaleFactor = 0.7f
        )
    }
}

data class AddHabitModalBottomSheet(
    val isShown: MutableState<Boolean>
) {
    fun show() {
        isShown.value = true
    }

    fun hide() {
        isShown.value = false
    }
}