package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable

@Composable
fun Steppable.InitViewStep6() {
    InitDataStepView(
        title = ui.texts.value.initStep6Title,
        onNext = { true }
    ) {

    }
}