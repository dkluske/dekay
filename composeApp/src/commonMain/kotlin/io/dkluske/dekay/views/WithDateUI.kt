package io.dkluske.dekay.views

import androidx.compose.runtime.MutableState
import io.dkluske.dekay.util.format.format
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class WithDateUI(
    ui: UI,
    private var currentDate: MutableState<Instant>
) : WithUI(ui = ui) {
    fun updateDate(date: Instant) {
        currentDate.value = date
    }

    fun currentDate(): Instant {
        return currentDate.value
    }

    fun isToday(): Boolean {
        return currentDate().toLocalDateTime(TimeZone.currentSystemDefault()).date == Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun formattedDate(): String {
        return if (isToday()) {
            ui.texts.value.today
        } else {
            currentDate().toLocalDateTime(TimeZone.currentSystemDefault()).date.format()
        }
    }
}