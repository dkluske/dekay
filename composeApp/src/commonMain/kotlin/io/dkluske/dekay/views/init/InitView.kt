package io.dkluske.dekay.views.init

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dekay.composeapp.generated.resources.Res
import dekay.composeapp.generated.resources.dekay_pill
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.components.ActionButton
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource

/**
 * TODO: i18N
 */
@Composable
fun InitView(
    ui: UI
) {
    val index = remember { mutableStateOf(0) }
    val stepMax = 5
    val steppable = object : Steppable {

        override fun previous() {
            index.value = index.value.minus(1).coerceAtLeast(0)
        }

        override fun next() {
            index.value = index.value.plus(1).coerceAtMost(stepMax)
        }
    }
    val settingsBuilder = SettingsBuilder()

    val step1BuilderFun: SettingsBuilder.(String, String, String?) -> Unit =
        { firstName, lastName, nickName ->
            firstName(firstName).lastName(lastName).nickName(nickName)
        }

    val step2BuilderFun: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit =
        { dateOfBirth, height, gender ->
            dateOfBirth(dateOfBirth).height(height).gender(gender)
        }

    val step3BuilderFun: SettingsBuilder.(Int) -> Unit = { dailyStepTarget ->
        dailyStepTarget(dailyStepTarget)
    }

    with(steppable) {
        when (index.value) {
            0 -> InitViewStart()
            
            1 -> InitViewStep1(
                settingsBuilder = settingsBuilder,
                step1Builder = step1BuilderFun
            )

            2 -> InitViewStep2(
                settingsBuilder = settingsBuilder,
                step2Builder = step2BuilderFun
            )

            3 -> InitViewStep3(
                settingsBuilder = settingsBuilder,
                step3Builder = step3BuilderFun
            )

            4 -> InitViewFinish()

            5 -> {
                val settings = settingsBuilder.build()
                ui.database.value.settingsQueries.update(settings.toDatabaseModel())
                ui.state.value = View.Home()
            }
        }
    }
}

@Composable
fun Steppable.InitViewStart() {
    InitWrapperStepView(
        title = null,
        text = null,
        nextButtonText = "Start"
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
                    text = """
                        In order to use dekay properly, we need some information from you in a few simple steps.
                        
                        No worries wo won't sell any of them.
                    """.trimIndent()
                )
            }
        }
    }
}

@Composable
fun Steppable.InitViewStep1(
    settingsBuilder: SettingsBuilder,
    step1Builder: SettingsBuilder.(String, String, String?) -> Unit
) {
    val firstName = remember { mutableStateOf<String?>(null) }
    val lastName = remember { mutableStateOf<String?>(null) }
    val nickName = remember { mutableStateOf<String?>(null) }
    val onNext: () -> Boolean = {
        if (firstName.value == null || lastName.value == null) {
            // TODO: show error
            false
        } else {
            settingsBuilder.step1Builder(firstName.value!!, lastName.value!!, nickName.value)
            true
        }
    }
    
    InitDataStepView(
        title = "Step 1: Personal Information",
        text = "Please enter your personal information to get started.",
        onNext = onNext
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(end = 4.dp)
                ) {
                    TextField(
                        value = firstName.value ?: "",
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        placeholder = { Text("Firstname") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0x2F2F2F),
                            unfocusedContainerColor = Color(0x2F2F2F),
                            disabledContainerColor = Color(0x2F2F2F),
                            errorContainerColor = Color(0x2F2F2F)
                        ),
                        onValueChange = {
                            firstName.value = it
                        }
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(start = 4.dp)
                ) {
                    TextField(
                        value = lastName.value ?: "",
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                        placeholder = { Text("Lastname") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0x2F2F2F),
                            unfocusedContainerColor = Color(0x2F2F2F),
                            disabledContainerColor = Color(0x2F2F2F),
                            errorContainerColor = Color(0x2F2F2F)
                        ),
                        onValueChange = {
                            lastName.value = it
                        }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextField(
                    value = nickName.value ?: "",
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    placeholder = { Text("Nickname") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0x2F2F2F),
                        unfocusedContainerColor = Color(0x2F2F2F),
                        disabledContainerColor = Color(0x2F2F2F),
                        errorContainerColor = Color(0x2F2F2F)
                    ),
                    onValueChange = {
                        nickName.value = it
                    }
                )
            }
        }
    }
}

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit
) {
    val dateOfBirth = mutableStateOf(LocalDate(2000, 1, 1))
    val height = mutableStateOf(180)
    val gender = mutableStateOf(Settings.Gender.NOT_DEFINED)
    val onNext: () -> Boolean = {
        settingsBuilder.step2Builder(dateOfBirth.value, height.value, gender.value)
        true
    }
    
    InitDataStepView(
        title = "Step 2: Health Information",
        text = "Please enter your health information to get started.",
        onNext = onNext
    ) {
        
    }
}

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {
    val stepGoal = mutableStateOf(10000)
    val onNext: () -> Boolean = {
        settingsBuilder.step3Builder(stepGoal.value)
        true
    }
    
    InitDataStepView(
        title = "Step 3: Daily Step Goal",
        text = "Please enter your daily step goal to get started.",
        onNext = onNext
    ) {
        
    }
}

@Composable
fun Steppable.InitViewFinish() {
    InitWrapperStepView(
        title = "Finished!",
        text = "Congratulations! You have finished the onboarding process. You are now ready to begin your journey with dekay.",
        nextButtonText = "Start your journey!"
    ) {
        
    }
}

@Composable
fun Steppable.InitWrapperStepView(
    title: String?,
    text: String?,
    nextButtonText: String,
    block: @Composable Steppable.() -> Unit
) {
    InitViewBase {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            title?.let {
                StepTitle(it)
            }
            text?.let {
                StepText(it)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            block()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(
                text = nextButtonText,
                onClick = {
                    next()
                }
            )
        }
    }
}

@Composable
fun Steppable.InitDataStepView(
    title: String,
    text: String,
    onNext: () -> Boolean,
    block: @Composable Steppable.() -> Unit
) {
    InitViewBase {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            StepTitle(title)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            StepText(text)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        ) {
            block()
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier.weight(1f)
                    .padding(end = 4.dp)
            ) {
                ActionButton(
                    text = "Back",
                    onClick = {
                        previous()
                    }
                )
            }
            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 4.dp)
            ) {
                ActionButton(
                    text = "Next",
                    onClick = {
                        if (onNext()) {
                             next()   
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Steppable.InitViewBase(
    block: @Composable Steppable.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(CUSTOM_THEME_DARK.background)
            .background(
                brush = object : ShaderBrush() {
                    override fun createShader(size: Size): Shader {
                        val maxSize = maxOf(size.width, size.height)
                        return RadialGradientShader(
                            colors = listOf(
                                Color(217f, 217f, 217f, 0.43f),
                                Color(166f, 166f, 166f, 0.26f),
                                Color(140f, 140f, 140f, 0.15f),
                                Color(115f, 115f, 115f, 0f)
                            ),
                            center = size.center.minus(Offset(x = 0f, y = size.height / 2)),
                            radius = maxSize / 1.2f,
                            colorStops = listOf(0.0f, 0.5f, 0.75f, 1f)
                        )
                    }
                }
            )
    ) {
        block()
    }
}

@Composable
private fun StepText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = CUSTOM_THEME_DARK.onPrimary
    )
}

@Composable
private fun StepTitle(text: String) {
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(200f, 200f, 200f)
    )
}

interface Steppable {
    fun previous()

    fun next()
}
