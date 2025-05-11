package io.dkluske.dekay.models.health.aggregatedrecord

import io.dkluske.dekay.models.health.HealthDataType

interface HealthAggregatedRecord {
    val dataType: HealthDataType
}