package io.dkluske.dekay

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.window.ComposeUIViewController
import io.dkluske.dekay.store.database.DriverFactory
import io.dkluske.dekay.util.Constants
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.localeIdentifier

fun MainViewController() = ComposeUIViewController {
    val sqlDriver = DriverFactory().createDriver(Constants.DB_NAME)

    App(
        sqlDriver = sqlDriver,
        language = Locale(NSLocale.currentLocale().localeIdentifier()).toLanguageTag()
    )
}