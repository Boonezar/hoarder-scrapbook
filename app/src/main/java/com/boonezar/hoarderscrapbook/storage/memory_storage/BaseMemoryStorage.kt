package com.boonezar.hoarderscrapbook.storage.memory_storage

interface BaseMemoryStorage<T> {
    fun get(): T
    fun save(data: T)
    fun clear()
}