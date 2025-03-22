package io.dkluske.dekay.store.model

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import io.dkluske.dekay.database.Ingredient as DBIngredient

@OptIn(ExperimentalUuidApi::class)
data class Ingredient(
    val id: Uuid,
    val name: String,
    val mealId: Uuid,
    val calories: Int,
    val protein: Int,
    val carbohydrates: Int,
    val fats: Int
) : DBModel<DBIngredient> {
    override fun toDatabaseModel(): DBIngredient {
        return DBIngredient(
            id_mostSigBits = id.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            id_leastSigBits = id.toLongs { _, leastSignificantBits -> leastSignificantBits },
            name = name,
            meal_id_mostSigBits = mealId.toLongs { mostSignificantBits, _ -> mostSignificantBits },
            meal_id_leastSigBits = mealId.toLongs { _, leastSignificantBits -> leastSignificantBits },
            calories = calories.toLong(),
            protein = protein.toLong(),
            carbohydrates = carbohydrates.toLong(),
            fats = fats.toLong()
        )
    }
}
