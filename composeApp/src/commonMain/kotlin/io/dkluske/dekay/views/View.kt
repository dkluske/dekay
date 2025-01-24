package io.dkluske.dekay.views

sealed interface View {
    class Home : View

    class Habits : View

    class Settings : View

    class Charts : View
}