package io.dkluske.dekay.views.init

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import io.dkluske.dekay.models.health.HealthDataType
import kotlinx.coroutines.launch

@Composable
fun Steppable.InitViewStep6() {
    val scope = rememberCoroutineScope()
    InitDataStepView(
        title = ui.texts.value.initStep6Title,
        onNext = { true }
    ) {
        Text(text = "Available: ${ui.health.value.isAvailable().getOrNull() == true}")
        InputButton(
            displayValue = ui.texts.value.manageHealthAccess
        ) {
            scope.launch {
                ui.health.value.requestAuthorization(
                    readTypes = listOf(
                        HealthDataType.Steps,
                        HealthDataType.ActiveCaloriesBurned,
                        HealthDataType.Sleep
                    ),
                    writeTypes = listOf()
                )
            }
        }
    }
}