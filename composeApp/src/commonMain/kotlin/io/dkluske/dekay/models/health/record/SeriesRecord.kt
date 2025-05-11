package io.dkluske.dekay.models.health.record

interface SeriesRecord<out T : Any> : IntervalRecord {
    val samples: List<T>
}