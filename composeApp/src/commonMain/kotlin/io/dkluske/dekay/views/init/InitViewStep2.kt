package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.components.wheelpicker.InfiniteCircularList
import io.dkluske.dekay.util.daysOfMonth
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate) -> Unit
) {
    val dayOfBirth = remember { mutableStateOf(1) }
    val monthOfBirth = remember { mutableStateOf(1) }
    val yearOfBirth = remember { mutableStateOf(1900) }
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val onNext: () -> Boolean = {
        // TODO: remove default values and add validation
        settingsBuilder.step2Builder(
            LocalDate(yearOfBirth.value, monthOfBirth.value, dayOfBirth.value)
        )
        true
    }

    InitDataStepView(
        title = ui.texts.value.initStep2Title,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    val daysOfMonth =
                        LocalDate(yearOfBirth.value, monthOfBirth.value, 1).daysOfMonth()
                    if (dayOfBirth.value > daysOfMonth) {
                        dayOfBirth.value = daysOfMonth
                    }

                    InfiniteCircularList(
                        width = 500.dp,
                        itemHeight = 35.dp,
                        items = (1..daysOfMonth step 1).toList(),
                        initialItem = dayOfBirth.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textColor = Color.DarkGray,
                        selectedTextColor = Color.Black,
                        onItemSelected = { _, item2 ->
                            dayOfBirth.value = item2
                        }
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    InfiniteCircularList(
                        width = 500.dp,
                        itemHeight = 35.dp,
                        items = (1..12 step 1).toList(),
                        initialItem = monthOfBirth.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textColor = Color.DarkGray,
                        selectedTextColor = Color.Black,
                        onItemSelected = { _, item2 ->
                            monthOfBirth.value = item2
                        }
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    InfiniteCircularList(
                        width = 500.dp,
                        itemHeight = 35.dp,
                        items = (1900..today.year step 1).toList(),
                        initialItem = yearOfBirth.value,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textColor = Color.DarkGray,
                        selectedTextColor = Color.Black,
                        onItemSelected = { _, item2 ->
                            yearOfBirth.value = item2
                        }
                    )
                }
            }
        }
    }
}