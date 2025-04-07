package io.dkluske.dekay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.cash.sqldelight.db.SqlDriver
import io.dkluske.dekay.database.Database
import io.dkluske.dekay.store.model.Settings
import io.dkluske.dekay.util.CUSTOM_THEME_DARK
import io.dkluske.dekay.util.Configuration
import io.dkluske.dekay.util.components.Card
import io.dkluske.dekay.util.localization.DeStrings
import io.dkluske.dekay.util.localization.EnStrings
import io.dkluske.dekay.views.UI
import io.dkluske.dekay.views.View
import io.dkluske.dekay.views.habits.HabitsView
import io.dkluske.dekay.views.home.HomeView
import io.dkluske.dekay.views.init.InitView
import io.dkluske.dekay.util.format.parseLocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    sqlDriver: SqlDriver,
    language: String
) {
    MaterialTheme(
        colors = CUSTOM_THEME_DARK
    ) {
        val database = Database(sqlDriver)
        val settings = database.settingsQueries.getSettings().executeAsOneOrNull()
        val init = settings == null
        val initialView = mutableStateOf(
            if (init) {
                View.Init()
            } else {
                View.Home()
            }
        )
        val ui = remember {
            UI(
                state = initialView,
                database = mutableStateOf(database),
                texts = mutableStateOf(
                    when (language) {
                        "de-DE" -> DeStrings
                        else -> EnStrings
                    }
                ),
                configuration = mutableStateOf(settings?.let {
                    Configuration(
                        name = it.first_name to it.last_name,
                        username = it.nick_name ?: it.first_name,
                        age = settings.date_of_birth.parseLocalDate(), // TODO: age calculation
                        height = it.height.toInt(),
                        dailyStepTarget = it.daily_step_target.toInt(),
                        gender = Settings.Gender.valueOf(it.gender.uppercase())
                    )
                } ?: Configuration())
            )
        }
        Scaffold(
            bottomBar = {
                if (ui.state.value !is View.Init) {
                    BottomAppBar(
                        containerColor = Color.Transparent
                    ) {
                        Card {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                IconButton(
                                    onClick = {
                                        ui.state.value = View.Home()
                                    }
                                ) {
                                    Icon(Icons.Default.Home, "home")
                                }
                                IconButton(
                                    onClick = {
                                        ui.state.value = View.Habits()
                                    }
                                ) {
                                    Icon(Icons.Default.Add, "habits")
                                }
                                IconButton(
                                    onClick = {
                                        ui.state.value = View.Charts()
                                    }
                                ) {
                                    Icon(Icons.Default.Done, "charts")
                                }
                                IconButton(
                                    onClick = {
                                        ui.state.value = View.Settings()
                                    }
                                ) {
                                    Icon(Icons.Default.Settings, "settings")
                                }
                            }
                        }
                    }
                }
            },
            backgroundColor = CUSTOM_THEME_DARK.background
        ) { paddingValues ->
            BoxWithConstraints(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(modifier = Modifier.weight(9f)) {
                        when (ui.state.value) {
                            is View.Home -> HomeView(ui = ui)
                            is View.Charts -> TODO()
                            is View.Habits -> HabitsView(ui = ui)
                            is View.Settings -> TODO()
                            is View.Init -> InitView(ui = ui)
                        }
                    }
                }
            }
        }
    }
}
