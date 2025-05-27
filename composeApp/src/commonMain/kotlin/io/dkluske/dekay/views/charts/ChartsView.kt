package io.dkluske.dekay.views.charts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.dkluske.dekay.util.components.CardText
import io.dkluske.dekay.util.components.PaddedMaxWidthRow
import io.dkluske.dekay.views.WithUI
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

private enum class TimeFrame(val startDate: (Instant, TimeZone) -> Instant) {
    DAY(
        startDate = { endDate, zone ->
            endDate.toLocalDateTime(zone).date.atStartOfDayIn(zone)
        }
    ),
    WEEK(
        startDate = { endDate, zone ->
            endDate.toLocalDateTime(zone).date.minus(1, DateTimeUnit.WEEK).atStartOfDayIn(zone)
        }
    ),
    MONTH(
        startDate = { endDate, zone ->
            endDate.toLocalDateTime(zone).date.minus(1, DateTimeUnit.MONTH).atStartOfDayIn(zone)
        }
    )
}

@Composable
fun WithUI.ChartsView() {
    val currentTime = Clock.System.now()
    val currentTimeZone = TimeZone.currentSystemDefault()
    val timeState = remember { mutableStateOf(TimeFrame.DAY) }
    val startTime = timeState.value.startDate(currentTime, currentTimeZone)

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(bottom = 35.dp)
    ) {
        item {
            PaddedMaxWidthRow {
                CardText(text = ui.texts.value.charts, scaleFactor = 1.5f)
            }
        }
        item {
            PaddedMaxWidthRow(
                modifier = Modifier.padding(12.dp)
            ) {
                PaddedMaxWidthRow(
                    modifier = Modifier
                        .background(
                            color = Color(80, 80, 80, 255),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TimeFrame.entries.forEach { frame ->
                        TimeFrameButton(
                            selected = timeState.value == frame,
                            label = when (frame) {
                                TimeFrame.DAY -> ui.texts.value.day
                                TimeFrame.WEEK -> ui.texts.value.week
                                TimeFrame.MONTH -> ui.texts.value.month
                            }
                        ) {
                            timeState.value = frame
                        }
                    }
                }
            }
        }
        // TODO: charts here then
    }
}

@Composable
private fun TimeFrameButton(
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(150, 150, 150, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color(150, 150, 150, 255)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(label)
    }
}