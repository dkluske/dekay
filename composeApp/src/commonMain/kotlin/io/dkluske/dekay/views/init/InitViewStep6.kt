package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable
import com.viktormykhailiv.kmp.health.HealthDataType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun Steppable.InitViewStep6() {
    InitDataStepView(
        title = ui.texts.value.initStep6Title,
        onNext = { true }
    ) {
        InputButton(
            displayValue = ui.texts.value.manageHealthAccess
        ) {
            CoroutineScope(Dispatchers.IO).launch {
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