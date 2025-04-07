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
    val stepMax = 7
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

    val step2BuilderFun: SettingsBuilder.(LocalDate) -> Unit =
        { dateOfBirth ->
            settingsBuilder.value = dateOfBirth(dateOfBirth)
        }

    val step3BuilderFun: SettingsBuilder.(Int) -> Unit = { height ->
        settingsBuilder.value = height(height)
    }

    val step4BuilderFun: SettingsBuilder.(Settings.Gender) -> Unit = { gender ->
        settingsBuilder.value = gender(gender)
    }

    val step5BuilderFun: SettingsBuilder.(Int) -> Unit = { steps ->
        settingsBuilder.value = dailyStepTarget(steps)
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

            4 -> InitViewStep4(
                settingsBuilder = settingsBuilder.value,
                step4Builder = step4BuilderFun
            )

            5 -> InitViewStep5(
                settingsBuilder = settingsBuilder.value,
                step5Builder = step5BuilderFun
            )

            6 -> InitViewFinish()

            7 -> {
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
