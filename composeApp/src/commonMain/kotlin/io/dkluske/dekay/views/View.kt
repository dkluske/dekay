package io.dkluske.dekay.views

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
sealed interface View {
    class Home : View

    class Habits : View

    class Settings : View

    class Charts : View

    class Init : View

    class Workouts : View

    class MealDetail(val mealId: UUID) : View
}
