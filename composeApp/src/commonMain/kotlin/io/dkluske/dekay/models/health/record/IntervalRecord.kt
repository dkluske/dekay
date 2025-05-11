package io.dkluske.dekay.models.health.record

import kotlinx.datetime.Instant

interface IntervalRecord : HealthRecord {
    val startTime: Instant
    val endTime: Instant
}