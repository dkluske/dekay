package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import kotlinx.datetime.LocalDate

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
            0 -> InitViewStart(
                
            )
            
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

            4 -> InitViewFinish(
                
            )

            5 -> {
                ui.state.value = View.Home()
            }
        }
    }
}

@Composable
fun Steppable.InitViewStart(
    
) {
    InitWrapperStepView() {
        
    }
}

@Composable
fun Steppable.InitViewStep1(
    settingsBuilder: SettingsBuilder,
    step1Builder: SettingsBuilder.(String, String, String?) -> Unit
) {
    InitDataStepView() {
        
    }
}

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit
) {
    InitDataStepView() {
        
    }
}

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {
    InitDataStepView() {
        
    }
}

@Composable
fun Steppable.InitViewFinish(

) {
    InitWrapperStepView() {
        
    }
}

@Composable
fun Steppable.InitWrapperStepView(
    block: @Composable () -> Unit
) {
    InitViewBase() {
        
    }
}

@Composable
fun Steppable.InitDataStepView(
    block: @Composable () -> Unit
) {
    InitViewBase() {
        
    }
}

@Composable
fun Steppable.InitViewBase(
    block: @Composable () -> Unit
) {
    
}

interface Steppable {
    @Composable
    fun previous()

    @Composable
    fun next()
}
