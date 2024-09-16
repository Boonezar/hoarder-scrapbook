package com.boonezar.hoarderscrapbook.storage.memory_storage

import com.boonezar.hoarderscrapbook.models.MemoryDataWrapper
import javax.inject.Inject

class MemoryMemoryStorageImpl @Inject constructor(): MemoryMemoryStorage {
    private var _data: MemoryDataWrapper = MemoryDataWrapper(null)
    override fun get() =_data
    override fun save(data: MemoryDataWrapper) {
        _data = data
    }
    override fun clear() {
        _data = MemoryDataWrapper(null)
    }
}