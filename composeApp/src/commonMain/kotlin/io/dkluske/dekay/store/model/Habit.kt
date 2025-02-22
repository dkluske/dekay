package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Habit(
    val id: Uuid,
    val title: String,
    val description: String?
)