package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable

@Composable
fun Steppable.InitViewFinish() {
    InitWrapperStepView(
        title = "${ui.texts.value.finished}!",
        text = ui.texts.value.initStepFinishText,
        nextButtonText = "${ui.texts.value.startYourJourney}!"
    ) {

    }
}