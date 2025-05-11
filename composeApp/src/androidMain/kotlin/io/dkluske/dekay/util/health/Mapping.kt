package io.dkluske.dekay.util.health

import com.viktormykhailiv.kmp.health.HealthAggregatedRecord
import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.aggregatedrecord.ActiveCaloriesBurnedAggregatedRecord
import io.dkluske.dekay.models.health.aggregatedrecord.HeartRateAggregatedRecord
import io.dkluske.dekay.models.health.aggregatedrecord.SleepAggregatedRecord
import io.dkluske.dekay.models.health.aggregatedrecord.StepsAggregatedRecord
import io.dkluske.dekay.models.health.aggregatedrecord.WeightAggregatedRecord
import io.dkluske.dekay.models.health.record.ActiveCaloriesBurnedRecord
import io.dkluske.dekay.models.health.record.HealthRecord
import io.dkluske.dekay.models.health.record.HeartRateRecord
import io.dkluske.dekay.models.health.record.SleepSessionRecord
import io.dkluske.dekay.models.health.record.SleepStageType
import io.dkluske.dekay.models.health.record.StepsRecord
import io.dkluske.dekay.models.health.record.WeightRecord
import io.dkluske.dekay.models.health.util.Mass

/**
 * TO HEALTH KMP
 */
fun HealthDataType.toHealthKMPDataType(): com.viktormykhailiv.kmp.health.HealthDataType {
    return when (this) {
        HealthDataType.ActiveCaloriesBurned -> com.viktormykhailiv.kmp.health.HealthDataType.ActiveCaloriesBurned
        HealthDataType.HeartRate -> com.viktormykhailiv.kmp.health.HealthDataType.HeartRate
        HealthDataType.Sleep -> com.viktormykhailiv.kmp.health.HealthDataType.Sleep
        HealthDataType.Steps -> com.viktormykhailiv.kmp.health.HealthDataType.Steps
        HealthDataType.Weight -> com.viktormykhailiv.kmp.health.HealthDataType.Weight
    }
}

fun HealthRecord.toHealthKMPRecord(): com.viktormykhailiv.kmp.health.HealthRecord? {
    return when (this) {
        is ActiveCaloriesBurnedRecord -> {
            com.viktormykhailiv.kmp.health.records.ActiveCaloriesBurnedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                total = this.total
            )
        }

        is HeartRateRecord -> {
            com.viktormykhailiv.kmp.health.records.HeartRateRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                samples = this.samples.map { it.toHealthKMPSample() }
            )
        }

        is SleepSessionRecord -> {
            com.viktormykhailiv.kmp.health.records.SleepSessionRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                stages = this.stages.map { it.toHealthKMPSleepStage() }
            )
        }

        is StepsRecord -> {
            com.viktormykhailiv.kmp.health.records.StepsRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                count = this.count
            )
        }

        is WeightRecord -> {
            com.viktormykhailiv.kmp.health.records.WeightRecord(
                time = this.time,
                weight = this.weight.toHealthKMPMass()
            )
        }

        is ActiveCaloriesBurnedAggregatedRecord -> {
            com.viktormykhailiv.kmp.health.aggregate.ActiveCaloriesBurnedAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                total = this.total
            )
        }

        is HeartRateAggregatedRecord -> {
            com.viktormykhailiv.kmp.health.aggregate.HeartRateAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg,
                min = this.min,
                max = this.max
            )
        }

        is SleepAggregatedRecord -> {
            com.viktormykhailiv.kmp.health.aggregate.SleepAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                totalDuration = this.totalDuration
            )
        }

        is StepsAggregatedRecord -> {
            com.viktormykhailiv.kmp.health.aggregate.StepsAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                count = this.count
            )
        }

        is WeightAggregatedRecord -> {
            com.viktormykhailiv.kmp.health.aggregate.WeightAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg.toHealthKMPMass(),
                min = this.min.toHealthKMPMass(),
                max = this.max.toHealthKMPMass()
            )
        }

        else -> null
    }
}

fun HeartRateRecord.Sample.toHealthKMPSample(): com.viktormykhailiv.kmp.health.records.HeartRateRecord.Sample {
    return com.viktormykhailiv.kmp.health.records.HeartRateRecord.Sample(
        time = this.time,
        beatsPerMinute = this.beatsPerMinute
    )
}

fun SleepSessionRecord.Stage.toHealthKMPSleepStage(): com.viktormykhailiv.kmp.health.records.SleepSessionRecord.Stage {
    return com.viktormykhailiv.kmp.health.records.SleepSessionRecord.Stage(
        startTime = this.startTime,
        endTime = this.endTime,
        type = when (this.type) {
            SleepStageType.Awake -> com.viktormykhailiv.kmp.health.records.SleepStageType.Awake
            SleepStageType.AwakeInBed -> com.viktormykhailiv.kmp.health.records.SleepStageType.AwakeInBed
            SleepStageType.Deep -> com.viktormykhailiv.kmp.health.records.SleepStageType.Deep
            SleepStageType.Light -> com.viktormykhailiv.kmp.health.records.SleepStageType.Light
            SleepStageType.OutOfBed -> com.viktormykhailiv.kmp.health.records.SleepStageType.OutOfBed
            SleepStageType.REM -> com.viktormykhailiv.kmp.health.records.SleepStageType.REM
            SleepStageType.Sleeping -> com.viktormykhailiv.kmp.health.records.SleepStageType.Sleeping
            SleepStageType.Unknown -> com.viktormykhailiv.kmp.health.records.SleepStageType.Unknown
        }
    )
}

fun Mass.toHealthKMPMass(): com.viktormykhailiv.kmp.health.units.Mass {
    return when (this.type) {
        Mass.Type.GRAMS -> com.viktormykhailiv.kmp.health.units.Mass.grams(this.inGrams)
        Mass.Type.KILOGRAMS -> com.viktormykhailiv.kmp.health.units.Mass.kilograms(this.inKilograms)
        Mass.Type.MILLIGRAMS -> com.viktormykhailiv.kmp.health.units.Mass.milligrams(this.inMilligrams)
        Mass.Type.MICROGRAMS -> com.viktormykhailiv.kmp.health.units.Mass.micrograms(this.inMicrograms)
        Mass.Type.OUNCES -> com.viktormykhailiv.kmp.health.units.Mass.ounces(this.inOunces)
        Mass.Type.POUNDS -> com.viktormykhailiv.kmp.health.units.Mass.pounds(this.inPounds)
    }
}

/**
 * FROM HEALTH KMP
 */
fun com.viktormykhailiv.kmp.health.HealthRecord.fromHealthKMPRecord(): HealthRecord? {
    return when (this) {
        is com.viktormykhailiv.kmp.health.records.ActiveCaloriesBurnedRecord -> {
            ActiveCaloriesBurnedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                total = this.total
            )
        }

        is com.viktormykhailiv.kmp.health.records.HeartRateRecord -> {
            HeartRateRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                samples = this.samples.map { it.fromHealthKMPSample() }
            )
        }

        is com.viktormykhailiv.kmp.health.records.SleepSessionRecord -> {
            SleepSessionRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                stages = this.stages.map { it.fromHealthKMPSleepStage() }
            )
        }

        is com.viktormykhailiv.kmp.health.records.StepsRecord -> {
            StepsRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                count = this.count
            )
        }

        is com.viktormykhailiv.kmp.health.records.WeightRecord -> {
            WeightRecord(
                time = this.time,
                weight = this.weight.fromHealthKMPMass()
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.ActiveCaloriesBurnedAggregatedRecord -> {
            ActiveCaloriesBurnedAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                total = this.total
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.HeartRateAggregatedRecord -> {
            HeartRateAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg,
                min = this.min,
                max = this.max
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.SleepAggregatedRecord -> {
            SleepAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                totalDuration = this.totalDuration
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.StepsAggregatedRecord -> {
            StepsAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                count = this.count
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.WeightAggregatedRecord -> {
            WeightAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg.fromHealthKMPMass(),
                min = this.min.fromHealthKMPMass(),
                max = this.max.fromHealthKMPMass()
            )
        }

        else -> null
    }
}

fun HealthAggregatedRecord.fromHealthKMPHealthAggregatedRecord(): io.dkluske.dekay.models.health.aggregatedrecord.HealthAggregatedRecord? {
    return when (this) {
        is com.viktormykhailiv.kmp.health.aggregate.ActiveCaloriesBurnedAggregatedRecord -> {
            ActiveCaloriesBurnedAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                total = this.total
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.HeartRateAggregatedRecord -> {
            HeartRateAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg,
                min = this.min,
                max = this.max
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.SleepAggregatedRecord -> {
            SleepAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                totalDuration = this.totalDuration
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.StepsAggregatedRecord -> {
            StepsAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                count = this.count
            )
        }

        is com.viktormykhailiv.kmp.health.aggregate.WeightAggregatedRecord -> {
            WeightAggregatedRecord(
                startTime = this.startTime,
                endTime = this.endTime,
                avg = this.avg.fromHealthKMPMass(),
                min = this.min.fromHealthKMPMass(),
                max = this.max.fromHealthKMPMass()
            )
        }

        else -> null
    }
}

fun com.viktormykhailiv.kmp.health.records.HeartRateRecord.Sample.fromHealthKMPSample(): HeartRateRecord.Sample {
    return io.dkluske.dekay.models.health.record.HeartRateRecord.Sample(
        time = this.time,
        beatsPerMinute = this.beatsPerMinute
    )
}

fun com.viktormykhailiv.kmp.health.records.SleepSessionRecord.Stage.fromHealthKMPSleepStage(): SleepSessionRecord.Stage {
    return io.dkluske.dekay.models.health.record.SleepSessionRecord.Stage(
        startTime = this.startTime,
        endTime = this.endTime,
        type = when (this.type) {
            com.viktormykhailiv.kmp.health.records.SleepStageType.Awake -> io.dkluske.dekay.models.health.record.SleepStageType.Awake
            com.viktormykhailiv.kmp.health.records.SleepStageType.AwakeInBed -> io.dkluske.dekay.models.health.record.SleepStageType.AwakeInBed
            com.viktormykhailiv.kmp.health.records.SleepStageType.Deep -> io.dkluske.dekay.models.health.record.SleepStageType.Deep
            com.viktormykhailiv.kmp.health.records.SleepStageType.Light -> io.dkluske.dekay.models.health.record.SleepStageType.Light
            com.viktormykhailiv.kmp.health.records.SleepStageType.OutOfBed -> io.dkluske.dekay.models.health.record.SleepStageType.OutOfBed
            com.viktormykhailiv.kmp.health.records.SleepStageType.REM -> io.dkluske.dekay.models.health.record.SleepStageType.REM
            com.viktormykhailiv.kmp.health.records.SleepStageType.Sleeping -> io.dkluske.dekay.models.health.record.SleepStageType.Sleeping
            com.viktormykhailiv.kmp.health.records.SleepStageType.Unknown -> io.dkluske.dekay.models.health.record.SleepStageType.Unknown
        }
    )
}

fun com.viktormykhailiv.kmp.health.units.Mass.fromHealthKMPMass(): Mass {
    return io.dkluske.dekay.models.health.util.Mass.grams(this.inGrams)
}