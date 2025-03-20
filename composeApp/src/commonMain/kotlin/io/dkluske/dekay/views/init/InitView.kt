package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.views.UI
import kotlinx.datetime.LocalDate

@Composable
fun InitView(
    ui: UI
) {
    val index = remember { mutableStateOf(0) }
    val stepMax = 4
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
        }
    }
}

@Composable
fun Steppable.InitViewStart(
    
) {
    
}

@Composable
fun Steppable.InitViewStep1(
    settingsBuilder: SettingsBuilder,
    step1Builder: SettingsBuilder.(String, String, String?) -> Unit
) {

}

@Composable
fun Steppable.InitViewStep2(
    settingsBuilder: SettingsBuilder,
    step2Builder: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit
) {

}

@Composable
fun Steppable.InitViewStep3(
    settingsBuilder: SettingsBuilder,
    step3Builder: SettingsBuilder.(Int) -> Unit
) {

}

@Composable
fun Steppable.InitViewFinish(

) {

}

interface Steppable {
    @Composable
    fun previous()

    @Composable
    fun next()
}
