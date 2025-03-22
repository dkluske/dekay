package io.dkluske.dekay

import androidx.compose.ui.window.singleWindowApplication
import io.dkluske.dekay.store.database.DriverFactory
import io.dkluske.dekay.util.Constants

fun main() = singleWindowApplication {
    val sqlDriver = DriverFactory().createDriver(Constants.DB_NAME)

    App(
        sqlDriver = sqlDriver
    )
}