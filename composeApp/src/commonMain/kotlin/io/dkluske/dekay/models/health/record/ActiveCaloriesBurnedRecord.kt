package io.dkluske.dekay.models.health.record

import io.dkluske.dekay.models.health.HealthDataType
import kotlinx.datetime.Instant

class ActiveCaloriesBurnedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
) : IntervalRecord {
    override val dataType: HealthDataType = HealthDataType.ActiveCaloriesBurned

    init {
        require(startTime <= endTime) { "startTime must be before endTime." }
        require(total >= 0) { "total must be higher or equal 0 calories" }
    }
}