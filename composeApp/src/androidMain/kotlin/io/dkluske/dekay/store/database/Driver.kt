package io.dkluske.dekay.store.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.dkluske.dekay.database.Database

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(dbName: String): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "$dbName.db")
    }
}