package io.dkluske.dekay.store.model

interface DBModel<T> {
    fun toDatabaseModel(): T
}