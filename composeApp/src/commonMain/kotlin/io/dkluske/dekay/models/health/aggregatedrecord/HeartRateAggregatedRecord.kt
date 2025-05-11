package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.record.IntervalRecord
import kotlinx.datetime.Instant

class HeartRateAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val avg: Long,
    val min: Long,
    val max: Long,
) : HealthAggregatedRecord,
    IntervalRecord {

    override val dataType: HealthDataType = HealthDataType.HeartRate
}