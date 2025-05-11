package io.dkluske.dekay.models.health.record

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.util.Mass
import io.dkluske.dekay.models.health.util.kilograms
import kotlinx.datetime.Instant

data class WeightRecord(
    override val time: Instant,
    val weight: Mass,
) : InstantRecord {

    override val dataType: HealthDataType = HealthDataType.Weight

    init {
        require(weight >= weight.zero()) { "weight must be higher or equal 0" }
        require(weight <= 1000.kilograms) { "weight must be lower or equal 1000 kg" }
    }
}