package io.dkluske.dekay.util

data class Configuration(
    val name: Pair<String, String?> = "User" to null,
    val username: String = "user",
    val age: Int = 1,
    val height: Int = 1
)