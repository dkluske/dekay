package io.dkluske.dekay.views

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.viktormykhailiv.kmp.health.HealthManager
import io.dkluske.dekay.database.Database
import io.dkluske.dekay.util.Configuration
import io.dkluske.dekay.util.localization.Strings

data class UI(
    val state: MutableState<View>,
    val database: MutableState<Database>,
    val configuration: MutableState<Configuration> = mutableStateOf(Configuration()),
    val texts: MutableState<Strings>,
    val health: MutableState<HealthManager>
)