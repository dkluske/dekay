package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable
import io.dkluske.dekay.views.init.InputButton

@Composable
fun Steppable.InitViewStep6() {
    InitDataStepView(
        title = ui.texts.value.initStep6Title,
        onNext = { true }
    ) {
        InputButton(
            displayValue = ui.texts.value.manageHealthAccess
        ) {
            // TODO: open health managing
        }
    }
}