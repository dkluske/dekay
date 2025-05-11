package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.record.IntervalRecord
import kotlinx.datetime.Instant

class StepsAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val count: Long,
) : HealthAggregatedRecord,
    IntervalRecord {

    override val dataType: HealthDataType = HealthDataType.Steps
}
