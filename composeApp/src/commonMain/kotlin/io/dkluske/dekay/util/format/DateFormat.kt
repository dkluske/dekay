package io.dkluske.dekay.util.format

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.char

val dateTimeFormat: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    year()
    char('-')
    monthNumber()
    char('-')
    dayOfMonth()
    char('T')
    hour()
    char(':')
    minute()
    char(':')
    second()
    char('.')
    secondFraction(3)
}

val dateFormat: DateTimeFormat<LocalDate> = LocalDate.Format {
    year()
    char('-')
    monthNumber()
    char('-')
    dayOfMonth()
}

fun String.parseLocalDate(): LocalDate {
    return LocalDate.parse(this, dateFormat)
}

fun String.parseLocalDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, dateTimeFormat)
}

fun LocalDate.format(): String {
    return this.format(dateFormat)
}

fun LocalDateTime.format(): String {
    return this.format(dateTimeFormat)
}