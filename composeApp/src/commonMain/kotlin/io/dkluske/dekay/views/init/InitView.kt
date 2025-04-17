package io.dkluske.dekay.views.init

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.store.model.SettingsBuilder
import io.dkluske.dekay.util.Configuration
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.WithUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@Composable
fun WithUI.InitView() {
    val stepMax = 8
    val coroutineScope = rememberCoroutineScope()
    val settingsBuilder = remember { mutableStateOf(SettingsBuilder()) }
    val pages = (0..stepMax).toList()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })

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

    val steppable = object : Steppable {
        override val ui: UI
            get() = this@InitView.ui
        override val scope: CoroutineScope
            get() = coroutineScope

        override fun previous() {
            val backPage = pagerState.currentPage - 1
            if (backPage >= 0) {
                scope.launch { pagerState.animateScrollToPage(backPage) }
            }
        }

        override fun next() {
            val nextPage = pagerState.currentPage + 1
            if (nextPage <= (pagerState.pageCount - 1)) {
                scope.launch { pagerState.animateScrollToPage(nextPage) }
            }
        }
    }

    with(steppable) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
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

                6 -> InitViewStep6()

                7 -> InitViewFinish()

                8 -> {
                    val settings = settingsBuilder.value.build()
                    ui.database.value.settingsQueries.update(settings.toDatabaseModel())
                    ui.configuration.value = Configuration(
                        name = settings.firstName to settings.lastName,
                        username = settings.nickName ?: settings.firstName,
                        age = settings.dateOfBirth ?: LocalDate(1970, 1, 1),
                        height = settings.height,
                        dailyStepTarget = settings.dailyStepTarget,
                        gender = settings.gender
                    )
                    ui.state.value = View.Home()
                }
            }
        }
    }
}

interface Steppable {
    val ui: UI
    val scope: CoroutineScope

    fun previous()

    fun next()
}
