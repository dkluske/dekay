package io.dkluske.dekay

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform