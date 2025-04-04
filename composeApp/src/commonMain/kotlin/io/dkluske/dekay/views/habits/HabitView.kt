@file:OptIn(ExperimentalUuidApi::class)

package io.dkluske.dekay.views.habits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.IconButtonColors
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
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.store.model.HabitEntry
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.AddButton
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.util.components.TextInput
import io.dkluske.dekay.util.format.format
import io.dkluske.dekay.util.format.parseLocalDate
import io.dkluske.dekay.views.UI
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Habit(
    val id: Uuid,
    val title: String,
    val targetHabitDays: List<DayOfWeek>,
    val checkedWeekdays: List<Pair<Uuid, DayOfWeek>>
)


@OptIn(ExperimentalUuidApi::class)
@Composable
fun HabitsView(
    ui: UI
) {
    val modalBottomSheet = AddHabitModalBottomSheet(
        isShown = remember { mutableStateOf(false) }
    )
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val startOfWeek = today.minus(
        value = today.dayOfWeek.isoDayNumber - 1,
        unit = DateTimeUnit.DAY
    )
    val endOfWeek = today.plus(
        value = 7 - today.dayOfWeek.isoDayNumber,
        unit = DateTimeUnit.DAY
    )

    val habitList = remember { mutableStateOf(emptyList<Habit>()) }
    val habitEntries = remember { mutableStateOf(emptyMap<Habit, List<HabitEntry>>()) }

    habitList.value = ui.database.value.habitQueries.selectAll().executeAsList().map { habit ->
        val lastEntries = ui.database.value.habitEntryQueries.selectLastXEntriesByHabitId(
            habit.id_mostSigBits,
            habit.id_leastSigBits,
            7
        ).executeAsList().map { entry ->
            HabitEntry(
                id = Uuid.fromLongs(entry.id_mostSigBits, entry.id_leastSigBits),
                habitId = Uuid.fromLongs(habit.id_mostSigBits, habit.id_leastSigBits),
                checkDate = entry.check_date.parseLocalDate()
            )
        }.sortedBy { entry -> entry.checkDate }.filter { entry ->
            entry.checkDate in startOfWeek..endOfWeek
        }
        val mappedHabit = Habit(
            id = Uuid.fromLongs(habit.id_mostSigBits, habit.id_leastSigBits),
            title = habit.title,
            targetHabitDays = DayOfWeek.entries,
            checkedWeekdays = lastEntries.map { entry -> entry.id to entry.checkDate.dayOfWeek },
        )
        habitEntries.value += mappedHabit to lastEntries

        mappedHabit
    }

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

        item {
            PaddedMaxWidthRow {
                Column {
                    habitList.value.forEach { habit ->
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
                                            checkedOnes = habit.checkedWeekdays.map { it.second }
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    val checked = remember { 
                                        mutableStateOf(
                                            habit.checkedWeekdays.map { it.second }
                                                .contains(today.dayOfWeek)
                                        ) 
                                    }
                                    Row {
                                        IconButton(
                                            onClick = {
                                                if (!checked.value) {
                                                    val entryId =
                                                        Uuid.random().toLongs { most, least ->
                                                            most to least
                                                        }
                                                    ui.database.value.habitEntryQueries.insert(
                                                        io.dkluske.dekay.database.HabitEntry(
                                                            id_mostSigBits = entryId.first,
                                                            id_leastSigBits = entryId.second,
                                                            habit_id_mostSigBits = habit.id.toLongs { most, _ -> most },
                                                            habit_id_leastSigBits = habit.id.toLongs { _, least -> least },
                                                            check_date = today.format()
                                                        )
                                                    )
                                                    checked.value = true
                                                } else {
                                                    habit.checkedWeekdays.first { it.second == today.dayOfWeek }
                                                        .let {
                                                            ui.database.value.habitEntryQueries.deleteById(
                                                                id_mostSigBits = it.first.toLongs { most, _ -> most },
                                                                id_leastSigBits = it.first.toLongs { _, least -> least }
                                                            )
                                                            checked.value = false
                                                        }
                                                }
                                            },
                                            colors = IconButtonColors(
                                                containerColor = Color.Transparent,
                                                contentColor = if (checked) {
                                                    Color(61, 255, 58, 255)
                                                } else {
                                                    Color(16, 16, 16, 255)
                                                },
                                                disabledContentColor = Color.DarkGray,
                                                disabledContainerColor = Color.Transparent
                                            )
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
        ui = ui,
        onAdd = {
            ui.database.value.habitQueries.insert(it)
            habitList.value += it.let {
                Habit(
                    id = Uuid.fromLongs(it.id_mostSigBits, it.id_leastSigBits),
                    title = it.title,
                    targetHabitDays = DayOfWeek.entries,
                    checkedWeekdays = emptyList()
                )
            }
        }
    )
}

@Composable
private fun HabitDays(
    checkedOnes: List<DayOfWeek>
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp).padding(start = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DayOfWeek.entries.toTypedArray().forEach { day ->
            val color = if (checkedOnes.contains(day)) {
                Color(110, 110, 110, 255)
            } else {
                Color(94, 94, 94, 255)
            }
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(20.dp)
                    .background(
                        color,
                        CircleShape
                    )
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "${day.name[0]}",
                    modifier = Modifier.padding(4.dp),
                    color = if (checkedOnes.contains(day)) {
                        Color.Green
                    } else Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
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
    ui: UI,
    onAdd: (io.dkluske.dekay.database.Habit) -> Unit
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
                .wrapContentHeight()
                .padding(bottom = 20.dp),
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

                        onAdd(
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
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp
            ),
            color = Color(200, 200, 200, 255)
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