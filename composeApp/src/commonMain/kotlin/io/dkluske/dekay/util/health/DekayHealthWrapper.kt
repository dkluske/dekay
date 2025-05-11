package io.dkluske.dekay.util.health

import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.aggregatedrecord.HealthAggregatedRecord
import io.dkluske.dekay.models.health.record.HealthRecord
import kotlinx.datetime.Instant

expect class DekayHealthWrapper() {
    fun isAvailable(): Result<Boolean>

    suspend fun isAuthorized(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean>

    suspend fun requestAuthorization(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean>

    suspend fun isRevokeAuthorizationSupported(): Result<Boolean>

    suspend fun revokeAuthorization(): Result<Unit>

    suspend fun readData(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<List<HealthRecord>>

    suspend fun writeData(
        records: List<HealthRecord>,
    ): Result<Unit>

    suspend fun aggregate(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<HealthAggregatedRecord>
}