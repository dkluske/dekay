package io.dkluske.dekay.views.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.components.CardList
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.CardWithFourContents
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.UI

@Composable
fun HomeView(
    ui: UI
) {
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
                CardWithFourContents(
                    upLeft = { CardText(text = "upLeft") },
                    upRight = { CardText(text = "upRight") },
                    downLeft = { CardText(text = "downLeft") },
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
