package io.dkluske.dekay.models.health

enum class HealthPermission {
    STEPS,
    CALORIES,
    WORKOUTS;
}

enum class HealthPermissionStatus {
    APPROVED,
    DENIED,
    NOT_PROVIDED;
}