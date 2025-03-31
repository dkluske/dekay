package io.dkluske.dekay.views.init

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dekay.composeapp.generated.resources.Res
import dekay.composeapp.generated.resources.dekay_pill
import org.jetbrains.compose.resources.painterResource

@Composable
fun Steppable.InitViewStart() {
    InitWrapperStepView(
        title = null,
        text = null,
        nextButtonText = ui.texts.value.start
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.5f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.dekay_pill),
                    contentDescription = "dekay logo"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.4f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StepText(
                    text = ui.texts.value.initStep1Text
                )
            }
        }
    }
}
