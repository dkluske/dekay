package io.dkluske.dekay.util.health

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.aggregatedrecord.HealthAggregatedRecord
import io.dkluske.dekay.models.health.record.HealthRecord
import kotlinx.datetime.Instant

actual class DekayHealthWrapper {

    actual fun isAvailable(): Result<Boolean> {
        return Result.success(false)
    }

    actual suspend fun isAuthorized(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean> {
        return Result.success(false)
    }

    actual suspend fun requestAuthorization(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean> {
        return Result.success(false)
    }

    actual suspend fun isRevokeAuthorizationSupported(): Result<Boolean> {
        return Result.success(false)
    }

    actual suspend fun revokeAuthorization(): Result<Unit> {
        return Result.failure(NotImplementedError())
    }

    actual suspend fun readData(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<List<HealthRecord>> {
        return Result.success(emptyList())
    }

    actual suspend fun writeData(
        records: List<HealthRecord>,
    ): Result<Unit> {
        return Result.failure(NotImplementedError())
    }

    actual suspend fun aggregate(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<HealthAggregatedRecord> {
        return Result.failure(NotImplementedError())
    }
}