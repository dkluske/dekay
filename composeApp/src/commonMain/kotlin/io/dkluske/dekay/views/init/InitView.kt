package io.dkluske.dekay.views.init

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.Configuration
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
        override val ui: UI
            get() = ui

        override fun previous() {
            index.value = index.value.minus(1).coerceAtLeast(0)
        }

        override fun next() {
            index.value = index.value.plus(1).coerceAtMost(stepMax)
        }
    }
    var settingsBuilder = remember { mutableStateOf(SettingsBuilder()) }

    val step1BuilderFun: SettingsBuilder.(String, String, String?) -> Unit =
        { firstName, lastName, nickName ->
            settingsBuilder.value = firstName(firstName).lastName(lastName).nickName(nickName)
        }

    val step2BuilderFun: SettingsBuilder.(LocalDate, Int, Settings.Gender) -> Unit =
        { dateOfBirth, height, gender ->
            settingsBuilder.value = dateOfBirth(dateOfBirth).height(height).gender(gender)
        }

    val step3BuilderFun: SettingsBuilder.(Int) -> Unit = { dailyStepTarget ->
        settingsBuilder.value = dailyStepTarget(dailyStepTarget)
    }

    with(steppable) {
        when (index.value) {
            0 -> InitViewStart()
            
            1 -> InitViewStep1(
                settingsBuilder = settingsBuilder.value,
                step1Builder = step1BuilderFun
            )

            2 -> InitViewStep2(
                settingsBuilder = settingsBuilder.value,
                step2Builder = step2BuilderFun
            )

            3 -> InitViewStep3(
                settingsBuilder = settingsBuilder.value,
                step3Builder = step3BuilderFun
            )

            4 -> InitViewFinish()

            5 -> {
                val settings = settingsBuilder.value.build()
                ui.database.value.settingsQueries.update(settings.toDatabaseModel())
                ui.configuration.value = Configuration(
                    name = settings.firstName to settings.lastName,
                    username = settings.nickName ?: settings.firstName,
                    age = 18, // TODO: calc from birthdate
                    height = settings.height ?: 180,
                    dailyStepTarget = settings.dailyStepTarget ?: 10000,
                    gender = settings.gender
                )
                ui.state.value = View.Home()
            }
        }
    }
}

interface Steppable {
    val ui: UI
    fun previous()

    fun next()
}
