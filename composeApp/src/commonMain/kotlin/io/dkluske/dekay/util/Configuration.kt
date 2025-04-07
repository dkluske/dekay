package io.dkluske.dekay.util

import io.dkluske.dekay.store.model.Settings
import kotlinx.datetime.LocalDate

data class Configuration(
    val name: Pair<String, String?> = "User" to null,
    val username: String = "user",
    val age: LocalDate = LocalDate(1970, 1, 1),
    val height: Int = 1,
    val dailyStepTarget: Int = 0,
    val gender: Settings.Gender = Settings.Gender.NOT_DEFINED
)

enum class Gender {
    MALE,
    FEMALE,
    NOT_DEFINED
}
