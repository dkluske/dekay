package io.dkluske.dekay.views.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.viktormykhailiv.kmp.health.HealthDataType
import com.viktormykhailiv.kmp.health.records.ActiveCaloriesBurnedRecord
import com.viktormykhailiv.kmp.health.records.StepsRecord
import io.dkluske.dekay.util.components.CardList
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.CardWithFourContents
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.WithUI
import kotlinx.datetime.*

@Composable
fun WithUI.HomeView() {
    val activeCalories = mutableStateOf<Int?>(null)
    val stepsToday = mutableStateOf<Int?>(null)
    val timeZone = TimeZone.currentSystemDefault()
    val current = Clock.System.now()
    val startOfDay = current.toLocalDateTime(timeZone).date.atStartOfDayIn(timeZone)

    LaunchedEffect(Unit) {
        activeCalories.value = (ui.health.value.readData(
            startTime = startOfDay,
            endTime = current,
            type = HealthDataType.ActiveCaloriesBurned
        ).getOrNull() as? ActiveCaloriesBurnedRecord).total?.toInt()
        stepsToday.value = (ui.health.value.readData(
            startTime = startOfDay,
            endTime = current,
            type = HealthDataType.Steps
        ).getOrNull() as? StepsRecord)?.count
    }

    LazyColumn(
        modifier = Modifier.padding(bottom = 35.dp)
    ) {
        item {
            PaddedMaxWidthRow {
                CardText(
                    text = "Hi ${ui.configuration.value.username}!",
                    scaleFactor = 1.5f
                )
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
