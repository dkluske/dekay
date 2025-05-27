package io.dkluske.dekay.views.workouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.WithDateUI

@Composable
fun WithDateUI.WorkoutsDayView() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            PaddedMaxWidthRow {
                CardText(text = ui.texts.value.workouts, scaleFactor = 1.5f)
            }
        }
        item {
            PaddedMaxWidthRow(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CardText(text = formattedDate(), scaleFactor = 1.1f)
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
                        // TODO: Workout minutes of this day
                    }
                }
                Column(
                    modifier = Modifier
                ) {
                    Card {
                        // TODO: Total calories of the workouts
                    }
                }
                Column(
                    modifier = Modifier
                ) {
                    Card {
                        // TODO: Count of workouts this day
                    }
                }
            }
        }
        val workouts = listOf<String>() // TODO: health pull workouts of that day

    }
}