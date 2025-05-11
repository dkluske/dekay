package io.dkluske.dekay.util.health

import com.viktormykhailiv.kmp.health.HealthManagerFactory
import io.dkluske.dekay.models.health.HealthDataType
import io.dkluske.dekay.models.health.aggregatedrecord.HealthAggregatedRecord
import io.dkluske.dekay.models.health.record.HealthRecord
import kotlinx.datetime.Instant

actual class DekayHealthWrapper {
    val health = HealthManagerFactory().createManager()

    actual fun isAvailable(): Result<Boolean> {
        return health.isAvailable()
    }

    actual suspend fun isAuthorized(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean> {
        return health.isAuthorized(
            readTypes.map { it.toHealthKMPDataType() },
            writeTypes.map { it.toHealthKMPDataType() }
        )
    }

    actual suspend fun requestAuthorization(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>,
    ): Result<Boolean> {
        return health.requestAuthorization(
            readTypes.map { it.toHealthKMPDataType() },
            writeTypes.map { it.toHealthKMPDataType() }
        )
    }

    actual suspend fun isRevokeAuthorizationSupported(): Result<Boolean> {
        return health.isRevokeAuthorizationSupported()
    }

    actual suspend fun revokeAuthorization(): Result<Unit> {
        return health.revokeAuthorization()
    }

    actual suspend fun readData(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<List<HealthRecord>> {
        return health.readData(
            startTime = startTime,
            endTime = endTime,
            type = type.toHealthKMPDataType()
        ).mapCatching {
            it.mapNotNull {
                it.fromHealthKMPRecord()
            }
        }
    }

    actual suspend fun writeData(
        records: List<HealthRecord>,
    ): Result<Unit> {
        return health.writeData(
            records.mapNotNull { it.toHealthKMPRecord() }
        )
    }

    actual suspend fun aggregate(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType,
    ): Result<HealthAggregatedRecord> {
        return health.aggregate(
            startTime = startTime,
            endTime = endTime,
            type = type.toHealthKMPDataType()
        ).mapCatching {
            it.fromHealthKMPHealthAggregatedRecord()
                ?: throw IllegalStateException("Aggregation cannot be mapped")
        }
    }
}