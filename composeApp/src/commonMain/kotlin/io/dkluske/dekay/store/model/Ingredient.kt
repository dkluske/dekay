package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class Ingredient(
    val id: Uuid,
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbohydrates: Int,
    val fats: Int
)
