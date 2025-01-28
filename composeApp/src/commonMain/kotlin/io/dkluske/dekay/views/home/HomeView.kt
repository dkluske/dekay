package io.dkluske.dekay.views.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import io.dkluske.dekay.util.components.CardList
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.CardWithFourContents
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.ViewWrapper

@Composable
fun HomeView(
    ui: UI
) {
    ViewWrapper {
        PaddedMaxWidthRow {
            Text(
                text = "Hi ${ui.configuration.value.username}"
            )
        }
        PaddedMaxWidthRow {
            // TODO: Add Health Data #4
            CardWithFourContents(
                upLeft = { CardText(text = "upLeft") },
                upRight = { CardText(text = "upRight") },
                downLeft = { CardText(text = "downLeft") },
                downRight = { CardText(text = "downRight") }
            )
        }
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
            )
        }

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
            )
        }
    }
}
