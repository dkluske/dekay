package io.dkluske.dekay.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

fun LocalDate.daysOfMonth(): Int {
    val leapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    return when (this.month) {
        Month.JANUARY -> 31
        Month.FEBRUARY -> if (leapYear) 29 else 28
        Month.MARCH -> 31
        Month.APRIL -> 30
        Month.MAY -> 31
        Month.JUNE -> 30
        Month.JULY -> 31
        Month.AUGUST -> 31
        Month.SEPTEMBER -> 30
        Month.OCTOBER -> 31
        Month.NOVEMBER -> 30
        Month.DECEMBER -> 31
        else -> 31
    }
}