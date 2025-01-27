package io.dkluske.dekay

actual fun getPlatform(): Platform {
    return object : Platform {
        override val name: String
            get() = "jvm"
    }
}