package com.boonezar.hoarderscrapbook.ui.views.memory_info

import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MemoryInfoViewModelFactory {
    fun create(memoryRepository: MemoryRepository): MemoryInfoViewModel
}