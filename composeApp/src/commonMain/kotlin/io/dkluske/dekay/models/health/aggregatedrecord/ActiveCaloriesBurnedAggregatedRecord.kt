package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.record.IntervalRecord
import kotlinx.datetime.Instant

class ActiveCaloriesBurnedAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
) : HealthAggregatedRecord,
    IntervalRecord {
    override val dataType: HealthDataType = HealthDataType.ActiveCaloriesBurned
}