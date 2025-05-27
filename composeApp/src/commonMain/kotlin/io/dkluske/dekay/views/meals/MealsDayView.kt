package io.dkluske.dekay.views.meals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.WithDateUI
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Composable
fun WithDateUI.MealsDayView() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            PaddedMaxWidthRow {
                CardText(text = ui.texts.value.meals, scaleFactor = 1.5f)
            }
        }
        item {
            PaddedMaxWidthRow {
                CardText(text = currentDate().toString(), scaleFactor = 1.1f)
                IconButton(
                    onClick = {} // TODO: calendar date picker
                ) {
                    Icon(Icons.Default.Today, "date")
                }
            }
        }
        item {
            PaddedMaxWidthRow {
                Column(
                    modifier = Modifier
                ) {
                    Card {
                        // TODO: Calories of this day
                    }
                }
                Column(
                    modifier = Modifier
                ) {
                    Card {
                        // TODO: Total Protein of the workouts
                    }
                }
            }
        }

        val meals = ui.database.value.mealQueries.selectAllByDate(
            currentDate().toLocalDateTime(
                TimeZone.currentSystemDefault()
            ).date.toString()
        ).executeAsList()

        items(meals.size) { mealIndex ->
            val meal = meals[mealIndex]
            Button(
                onClick = {
                    ui.state.value =
                        View.MealDetail(Uuid.fromLongs(meal.id_mostSigBits, meal.id_leastSigBits))
                }
            ) {
                Card {
                    CardText(text = meal.name)
                }
            }
        }
    }
}