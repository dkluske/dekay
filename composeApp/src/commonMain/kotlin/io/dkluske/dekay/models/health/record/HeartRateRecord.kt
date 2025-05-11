package io.dkluske.dekay.models.health.record

import io.dkluske.dekay.models.health.HealthDataType
import kotlinx.datetime.Instant

class HeartRateRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    override val samples: List<Sample>,
) : SeriesRecord<HeartRateRecord.Sample> {

    override val dataType: HealthDataType = HealthDataType.HeartRate

    init {
        require(startTime <= endTime) { "startTime must be before endTime." }
    }

    /**
     * Represents a single measurement of the heart rate.
     *
     * @param time The point in time when the measurement was taken.
     * @param beatsPerMinute Heart beats per minute. Validation range: 1-300.
     *
     * @see HeartRateRecord
     */
    data class Sample(
        override val time: Instant,
        val beatsPerMinute: Int,
    ) : InstantRecord {

        override val dataType: HealthDataType = HealthDataType.Steps

        init {
            require(beatsPerMinute >= 1) { "beatsPerMinute must be higher or equal 1" }
            require(beatsPerMinute <= 300) { "beatsPerMinute must be lower or equal 300" }
        }
    }
}