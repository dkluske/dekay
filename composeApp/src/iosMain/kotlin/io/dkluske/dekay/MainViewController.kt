package io.dkluske.dekay

import androidx.compose.ui.window.ComposeUIViewController
import io.dkluske.dekay.store.database.DriverFactory
import io.dkluske.dekay.util.Constants

fun MainViewController() = ComposeUIViewController {
    val sqlDriver = DriverFactory().createDriver(Constants.DB_NAME)

    App(
        sqlDriver = sqlDriver
    )
}