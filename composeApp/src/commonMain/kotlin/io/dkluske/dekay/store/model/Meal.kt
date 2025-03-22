package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import io.dkluske.dekay.database.Meal as DBMeal

@OptIn(ExperimentalUuidApi::class)
data class Meal(
    val id: Uuid,
    val name: String,
    val ingredients: List<Ingredient>
) : DBModel<DBMeal> {
    override fun toDatabaseModel(): DBMeal {
        return DBMeal(
            id_mostSigBits = id.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            id_leastSigBits = id.toLongs { _, leastSignificantBits -> leastSignificantBits },
            name = name
        )
    }
}
