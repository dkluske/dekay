package io.dkluske.dekay.views

import androidx.compose.runtime.MutableState
import kotlinx.datetime.Instant

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
}