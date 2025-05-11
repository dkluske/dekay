package io.dkluske.dekay.models.health.record

import io.dkluske.dekay.models.health.HealthDataType
import kotlinx.datetime.Instant

data class StepsRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val count: Int,
) : IntervalRecord {

    override val dataType: HealthDataType = HealthDataType.Steps

    init {
        require(count >= 1) { "count must be higher or equal 1" }
        require(count <= 1_000_000) { "count must be lower or equal 1_000_000" }
        require(startTime <= endTime) { "startTime must be before endTime." }
    }
}