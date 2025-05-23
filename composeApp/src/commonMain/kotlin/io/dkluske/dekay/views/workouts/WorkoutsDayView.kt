package io.dkluske.dekay.views.workouts

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

@Composable
fun WithUI.WorkoutsDayView() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            PaddedMaxWidthRow {
                CardText(text = ui.texts.value.workouts, scaleFactor = 1.5f)
            }
        }
        item {
            PaddedMaxWidthRow {
                CardText(text = Clock.System.now().toString(), scaleFactor = 1.1f)
                IconButton(
                    onClick = {} // TODO: calendar date picker
                ) {
                    //Icon(Icons.Default.Today, "date")
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