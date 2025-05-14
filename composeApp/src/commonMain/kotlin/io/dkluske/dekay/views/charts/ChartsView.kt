import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.background
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
import io.dkluske.dekay.views.WithUI
import kotlinx.datetime.*

private enum class TimeFrame(val startDate: (Instant, TimeZone) -> Instant) {
    DAY { endDate, zone ->
        endDate.toLocalDateTime(zone).date.atStartOfDayIn(zone)
    },
    WEEK { endDate, zone ->
        endDate.toLocalDateTime(zone).date.minus(1, DateTimeUnit.WEEK).atStartOfDayIn(zone)
    },
    MONTH { endDate, zone ->
        endDate.toLocalDateTime(zone).date.minus(1, DateTimeUnit.MONTH).atStartOfDayIn(zone)
    }
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
                modifier = Modifier
                    .background(Color(155, 155, 155, 255)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TimeFrame.entries.forEach { frame ->
                    TimeFrameButton(
                        selected = timeState.value == frame,
                        label = when (timeState.value) {
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
            containerColor = Color(200, 200, 200, 255),
            contentColor = Color.White,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color(200, 200, 200, 150)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(label)
    }
}