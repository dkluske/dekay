package io.dkluske.dekay.store.model

import kotlinx.datetime.LocalDate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class HabitEntry(
    val id: Uuid,
    val habitId: Uuid,
    val checkDate: LocalDate
)
