package io.dkluske.dekay.store.model

import kotlinx.datetime.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import io.dkluske.dekay.database.HabitEntry as DBHabitEntry

@OptIn(ExperimentalUuidApi::class)
data class HabitEntry(
    val id: Uuid,
    val habitId: Uuid,
    val checkDate: LocalDate
) : DBModel<DBHabitEntry> {
    override fun toDatabaseModel(): DBHabitEntry {
        return DBHabitEntry(
            id_mostSigBits = id.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            id_leastSigBits = id.toLongs { _, leastSignificantBits -> leastSignificantBits },
            habit_id_mostSigBits = habitId.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            habit_id_leastSigBits = habitId.toLongs { _, leastSignificantBits -> leastSignificantBits },
            check_date = checkDate.toString()
        )
    }
}
