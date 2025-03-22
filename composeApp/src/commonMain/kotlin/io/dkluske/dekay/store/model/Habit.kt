package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import io.dkluske.dekay.database.Habit as DBHabit

@OptIn(ExperimentalUuidApi::class)
data class Habit(
    val id: Uuid,
    val title: String,
    val description: String?
) : DBModel<DBHabit> {
    override fun toDatabaseModel(): DBHabit {
        return DBHabit(
            id_mostSigBits = id.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            id_leastSigBits = id.toLongs { _, leastSignificantBits -> leastSignificantBits },
            title = title,
            description = description
        )
    }
}