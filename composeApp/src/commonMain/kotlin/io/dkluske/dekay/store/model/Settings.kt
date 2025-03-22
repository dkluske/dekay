package io.dkluske.dekay.store.model

import kotlinx.datetime.LocalDate
import io.dkluske.dekay.database.Settings as DBSettings

data class Settings(
    val index: Int,
    val firstName: String,
    val lastName: String,
    val nickName: String?,
    val dailyStepTarget: Int?,
    val gender: Gender,
    val dateOfBirth: LocalDate?,
    val height: Int?
) : DBModel<DBSettings> {
    enum class Gender {
        MALE,
        FEMALE,
        NOT_DEFINED;
    }

    override fun toDatabaseModel(): DBSettings {
        return DBSettings(
            identifier = index.toLong(),
            first_name = firstName,
            last_name = lastName,
            nick_name = nickName,
            daily_step_target = dailyStepTarget?.toLong(),
            gender = gender.name,
            date_of_birth = dateOfBirth?.toString(),
            height = height?.toLong()
        )
    }
}

class SettingsBuilder {
    var firstName = ""
    var lastName = ""
    var nickName: String? = null
    var dailyStepTarget: Int? = null
    var gender = Settings.Gender.NOT_DEFINED
    var dateOfBirth: LocalDate? = null
    var height: Int? = null

    fun firstName(firstName: String) = apply { this.firstName = firstName }

    fun lastName(lastName: String) = apply { this.lastName = lastName }

    fun nickName(nickName: String?) = apply { this.nickName = nickName }

    fun dailyStepTarget(dailyStepTarget: Int?) = apply { this.dailyStepTarget = dailyStepTarget }

    fun gender(gender: Settings.Gender) = apply { this.gender = gender }

    fun dateOfBirth(dateOfBirth: LocalDate?) = apply { this.dateOfBirth = dateOfBirth }

    fun height(height: Int?) = apply { this.height = height }

    fun build() = Settings(
        index = 0,
        firstName = firstName,
        lastName = lastName,
        nickName = nickName,
        dailyStepTarget = dailyStepTarget,
        gender = gender,
        dateOfBirth = dateOfBirth,
        height = height
    )
}