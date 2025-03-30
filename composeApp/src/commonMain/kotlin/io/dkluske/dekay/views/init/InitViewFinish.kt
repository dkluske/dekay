package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable

@Composable
fun Steppable.InitViewFinish() {
    InitWrapperStepView(
        title = "Finished!",
        text = "Congratulations! You have finished the onboarding process. You are now ready to begin your journey with dekay.",
        nextButtonText = "Start your journey!"
    ) {

    }
}