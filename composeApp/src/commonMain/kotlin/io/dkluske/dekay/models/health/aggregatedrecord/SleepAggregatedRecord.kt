package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.record.IntervalRecord
import kotlinx.datetime.Instant
import kotlin.time.Duration

class SleepAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val totalDuration: Duration,
) : HealthAggregatedRecord,
    IntervalRecord {

    override val dataType: HealthDataType = HealthDataType.Sleep
}