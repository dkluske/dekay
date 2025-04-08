package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.components.wheelpicker.InfiniteCircularList

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {
    val height = mutableStateOf(170)
    val onNext: () -> Boolean = {
        settingsBuilder.step3Builder(height.value)
        true
    }

    InitDataStepView(
        title = ui.texts.value.initStep3Title,
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InfiniteCircularList(
                width = 500.dp,
                itemHeight = 35.dp,
                items = (100..250 step 1).toList(),
                initialItem = height.value,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textColor = Color.DarkGray,
                selectedTextColor = Color.Black,
                onItemSelected = { _, item2 ->
                    height.value = item2
                }
            )
        }
    }
}