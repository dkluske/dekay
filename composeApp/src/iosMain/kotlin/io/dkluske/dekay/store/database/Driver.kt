package io.dkluske.dekay.store.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.dkluske.dekay.database.Database

actual class DriverFactory {
    actual fun createDriver(dbName: String): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "$dbName.db")
    }
}