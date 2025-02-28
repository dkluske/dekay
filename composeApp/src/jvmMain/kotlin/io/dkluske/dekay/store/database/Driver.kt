package io.dkluske.dekay.store.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.dkluske.dekay.database.Database

actual class DriverFactory {
    actual fun createDriver(dbName: String): SqlDriver {
        return JdbcSqliteDriver(url = "jdbc:sqlite:$dbName.db", schema = Database.Schema)
    }
}