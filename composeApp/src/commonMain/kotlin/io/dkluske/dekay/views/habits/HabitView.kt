package io.dkluske.dekay.views.habits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.Weekday
import io.dkluske.dekay.util.components.AddButton
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
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
    val modalEnabled = remember { mutableStateOf(false) }
    Column {
        PaddedMaxWidthRow(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardText(
                text = "Habits",
                scaleFactor = 1.5f
            )
            AddButton {
                modalEnabled.value = true
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

        PaddedMaxWidthRow {
            LazyColumn {
                items(mockupData) { habit ->
                    Card {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row {
                                    CardText(habit.title)
                                }
                                Row {
                                    HabitDays(
                                        checkedOnes = habit.checkedWeekdays,
                                        checkable = false
                                    )
                                }
                            }
                            Column {
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

@Composable
private fun HabitDays(
    checkedOnes: List<Weekday>,
    checkable: Boolean
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        items(Weekday.entries.toTypedArray()) { day ->
            if (checkable) {
                Button(
                    onClick = {
                        // TODO: set checked
                    }
                ) {
                    Text("${day.initial}")
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(
                            Color.LightGray,
                            CircleShape
                        )
                ) {
                    day.initial.let {
                        if (checkedOnes.contains(day)) {
                            Text(text = "$it", color = Color.Green)
                        } else {
                            Text(text = "$it")
                        }
                    }
                }
            }
        }
    }
}