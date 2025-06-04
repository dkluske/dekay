package io.dkluske.dekay.logic.sleepscore

import kotlin.math.pow
import kotlin.math.max

object SleepCalculator {
    data class SleepData(
        val age: Int,
        val totalSleepTimeMillis: Long,
        val awakeTimeMillis: Long,
        val remSleepTimeMillis: Long,
        val deepSleepTimeMillis: Long,
        val lightSleepTimeMillis: Long
    )

    sealed interface SleepScoreCategory {
        val totalAvailablePoints: Int

        fun calculatePoints(): Double
    }

    class TotalSleepTime(
        val totalSleepTimeMillis: Long
    ) : SleepScoreCategory {
        override val totalAvailablePoints: Int = 30

        override fun calculatePoints(): Double {
            return if (totalSleepTimeMillis in optimalSleepTime) {
                totalAvailablePoints.toDouble()
            } else {
                val mean = (MIN_OPTIMAL_SLEEP_TIME + MAX_OPTIMAL_SLEEP_TIME) / 2L
                val distanceFromMean = totalSleepTimeMillis - mean

                totalAvailablePoints.toDouble() * calcPointMultiplicator(distanceFromMean)
            }
        }

        companion object {
            private const val MIN_OPTIMAL_SLEEP_TIME: Long = 25_200_000L
            private const val MAX_OPTIMAL_SLEEP_TIME: Long = 32_400_000L
            private val optimalSleepTime = (MIN_OPTIMAL_SLEEP_TIME..MAX_OPTIMAL_SLEEP_TIME)

            fun calcPointMultiplicator(distanceFromMean: Long): Double {
                return max(((-1000 * distanceFromMean.toDouble().pow(2.0)) + 1), 0.0)
            }
        }
    }

    fun calculateScore(data: SleepData): Double {
        val categories = buildList {
            add(
                TotalSleepTime(totalSleepTimeMillis = data.totalSleepTimeMillis).calculatePoints()
            )
        }

        return categories.sumOf { it }
    }
}