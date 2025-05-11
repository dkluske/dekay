package io.dkluske.dekay.models.health.record

import kotlinx.datetime.Instant

interface InstantRecord : HealthRecord {
    val time: Instant
}