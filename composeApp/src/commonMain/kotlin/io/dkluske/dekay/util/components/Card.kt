package io.dkluske.dekay.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.CUSTOM_THEME_DARK

@Composable
fun Card(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                CUSTOM_THEME_DARK.surface,
                RoundedCornerShape(8.dp)
            )
    ) {
        content()
    }
}

@Composable
fun CardWithFourContents(
    upLeft: @Composable () -> Unit,
    upRight: @Composable () -> Unit,
    downLeft: @Composable () -> Unit,
    downRight: @Composable () -> Unit
) {
    Card {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                upLeft()
            }
            Column(modifier = Modifier.weight(1f)) {
                upRight()
            }
        }
        Divider()
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                downLeft()
            }
            Column(modifier = Modifier.weight(1f)) {
                downRight()
            }
        }
    }
}

@Composable
fun <T : Any> CardList(
    maxItemsBeforeWrap: Int = 3,
    showMoreAction: (() -> Unit)? = null,
    itemList: Collection<T> = emptyList(),
    supplier: (T) -> String?
) {
    val cards = itemList
        .take(maxItemsBeforeWrap)
        .mapNotNull { supplier.invoke(it) }

    LazyColumn {
        items(cards) { item ->
            Card {
                CardText(item)
            }
        }
    }
    showMoreAction?.let {
        PaddedMaxWidthRow {
            Card {
                Button(
                    onClick = {
                        it.invoke()
                    },
                    content = {
                        CardText("Show more ->") // TODO: localization
                    }
                )
            }
        }
    }
}