package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.record.IntervalRecord
import io.dkluske.dekay.models.health.util.Mass
import kotlinx.datetime.Instant

class WeightAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val avg: Mass,
    val min: Mass,
    val max: Mass,
) : HealthAggregatedRecord,
    IntervalRecord {

    override val dataType: HealthDataType = HealthDataType.Weight
}