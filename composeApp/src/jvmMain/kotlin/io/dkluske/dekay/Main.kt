package io.dkluske.dekay

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import io.dkluske.dekay.store.database.DriverFactory
import io.dkluske.dekay.util.Constants
import java.util.Locale

fun main() = singleWindowApplication(
    title = Constants.APP_NAME,
    state = WindowState(
        width = 360.dp,
        height = 630.dp
    )
) {
    val sqlDriver = DriverFactory().createDriver(Constants.DB_NAME)

    App(
        sqlDriver = sqlDriver,
        language = Locale.getDefault().toLanguageTag()
    )
}