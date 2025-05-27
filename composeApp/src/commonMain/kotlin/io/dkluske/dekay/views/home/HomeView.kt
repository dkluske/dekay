package io.dkluske.dekay.views.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viktormykhailiv.kmp.health.aggregateActiveCaloriesBurned
import com.viktormykhailiv.kmp.health.aggregateSteps
import io.dkluske.dekay.util.components.CardList
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.CardWithFourContents
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.WithUI
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

@Composable
fun WithUI.HomeView() {
    val activeCalories = mutableStateOf<Int?>(null)
    val stepsToday = mutableStateOf<Int?>(null)
    val timeZone = TimeZone.currentSystemDefault()
    val current = Clock.System.now()
    val startOfDay = current.toLocalDateTime(timeZone).date.atStartOfDayIn(timeZone)

    LaunchedEffect(Unit) {
        activeCalories.value = ui.health.value.aggregateActiveCaloriesBurned(
            startTime = startOfDay,
            endTime = current
        ).getOrNull()?.total?.toInt()
        stepsToday.value = ui.health.value.aggregateSteps(
            startTime = startOfDay,
            endTime = current
        ).getOrNull()?.count?.toInt()
    }

    LazyColumn(
        modifier = Modifier.padding(bottom = 35.dp)
    ) {
        item {
            PaddedMaxWidthRow(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardText(
                    text = "Hi ${ui.configuration.value.username}!",
                    scaleFactor = 1.5f
                )
                IconButton(
                    onClick = {
                        ui.state.value = View.Settings()
                    }
                ) {
                    Icon(Icons.Default.Settings, "settings")
                }
            }
        }
        item {
            PaddedMaxWidthRow {
                // TODO: Add Health Data #4
                val activeCaloriesText = activeCalories.value?.let {
                    "$it kcal"
                } ?: "n/a"
                val stepsTodayText = stepsToday.value?.let {
                    "$it ${ui.texts.value.steps}"
                } ?: "n/a"

                CardWithFourContents(
                    upLeft = { CardText(text = activeCaloriesText) },
                    upRight = { CardText(text = "upRight") },
                    downLeft = { CardText(text = stepsTodayText) },
                    downRight = { CardText(text = "downRight") }
                )
            }
        }
        item {
            CardText(
                text = "Workouts",
                scaleFactor = 1.2f
            )
        }

        item {
            PaddedMaxWidthRow {
                // TODO: Add Workout Data #4
                val mockupData = listOf(
                    "Workout 1",
                    "Workout 2",
                    "Workout 3",
                    "Workout 4",
                    "Workout 5",
                    "Workout 6",
                    "Workout 7",
                )
                CardList(
                    itemList = mockupData,
                    supplier = { it }
                    // TODO: add showMoreAction when uis are built
                )
            }
        }

        item {
            CardText(
                text = "Meals",
                scaleFactor = 1.2f
            )
        }

        item {
            PaddedMaxWidthRow {
                // TODO: Add Meal Data #4
                val mockupData = listOf(
                    "Meal 1",
                    "Meal 2",
                    "Meal 3",
                    "Meal 4",
                    "Meal 5",
                )
                CardList(
                    itemList = mockupData,
                    supplier = { it }
                    // TODO: add showMoreAction when uis are built
                )
            }
        }
    }
}
