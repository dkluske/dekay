package io.dkluske.dekay.models.health.record

import io.dkluske.dekay.models.health.HealthDataType

interface HealthRecord {
    val dataType: HealthDataType
}