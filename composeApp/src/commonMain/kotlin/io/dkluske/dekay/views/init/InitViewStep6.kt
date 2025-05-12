package io.dkluske.dekay.views.init

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import com.viktormykhailiv.kmp.health.HealthDataType
import kotlinx.coroutines.launch

@Composable
fun Steppable.InitViewStep6() {
    val scope = rememberCoroutineScope()
    var isAuthorized by mutableStateOf(false)

    LaunchedEffect(Unit) {
        isAuthorized = ui.health.value.isAuthorized(
            readTypes = listOf(
                HealthDataType.Steps,
                HealthDataType.ActiveCaloriesBurned,
                HealthDataType.Sleep
            ),
            writeTypes = listOf()
        )
    }
    
    InitDataStepView(
        title = ui.texts.value.initStep6Title,
        onNext = { true }
    ) {
        InputButton(
            displayValue = if (isAuthorized) {
                ui.texts.value.revokeHealthAccess
            } else {
                ui.texts.value.authorizeHealthAccess
            }
            
        ) {
            scope.launch {
                if (isAuthorized) {
                    ui.health.value.revokeAuthorization()
                } else {
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
}