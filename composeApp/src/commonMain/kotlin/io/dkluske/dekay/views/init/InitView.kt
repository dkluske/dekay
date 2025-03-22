package io.dkluske.dekay.views.init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import kotlinx.datetime.LocalDate

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
        @Composable
        override fun previous() {
            index.value = index.value.minus(1).coerceAtLeast(0)
        }

        @Composable
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
        text = "Welcome to dekay!"
    ) {
        
    }
}

@Composable
fun Steppable.InitViewStep1(
    settingsBuilder: SettingsBuilder,
    step1Builder: SettingsBuilder.(String, String, String?) -> Unit
) {
    InitDataStepView(
        title = "Step 1: Personal Information",
        text = "Please enter your personal information to get started."
    ) {
        
    }
}

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit
) {
    InitDataStepView(
        title = "Step 2: Health Information",
        text = "Please enter your health information to get started."
    ) {
        
    }
}

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {
    InitDataStepView(
        title = "Step 3: Daily Step Goal",
        text = "Please enter your daily step goal to get started."
    ) {
        
    }
}

@Composable
fun Steppable.InitViewFinish() {
    InitWrapperStepView(
        title = "Finished!",
        text = "Congratulations! You have finished the onboarding process. You are now ready to begin your journey with dekay."
    ) {
        
    }
}

@Composable
fun Steppable.InitWrapperStepView(
    title: String?,
    text: String,
    block: @Composable () -> Unit
) {
    InitViewBase {
        
    }
}

@Composable
fun Steppable.InitDataStepView(
    title: String,
    text: String,
    block: @Composable () -> Unit
) {
    InitViewBase {
        
    }
}

@Composable
fun Steppable.InitViewBase(
    block: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CUSTOM_THEME_DARK.background)
            .background(
                brush = object : ShaderBrush() {
                    override fun createShader(size: Size): Shader {
                        val maxSize = maxOf(size.width, size.height)
                        return RadialGradientShader(
                            colors = listOf(
                                Color.White,
                                Color.Gray,
                                Color.LightGray,
                                Color.Transparent
                            ),
                            center = size.center.minus(Offset(x = 0f, y = 20f)),
                            radius = maxSize * 1.5f / 2f,
                            tileMode = TileMode.Clamp
                        )
                    }
                }
            )
    ) {
        block()
    }
}

interface Steppable {
    @Composable
    fun previous()

    @Composable
    fun next()
}
