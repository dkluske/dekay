package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Meal(
    val id: Uuid,
    val name: String,
    val ingredients: List<Ingredient>
)
