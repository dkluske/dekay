package io.dkluske.dekay.util

enum class Weekday {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    val initial: Char
        get() = this.toString()[0]
}