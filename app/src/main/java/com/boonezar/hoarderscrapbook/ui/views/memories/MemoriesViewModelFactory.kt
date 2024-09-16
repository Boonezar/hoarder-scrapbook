package com.boonezar.hoarderscrapbook.ui.views.memories

import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MemoriesViewModelFactory {
    fun create(memoryRepository: MemoryRepository): MemoriesViewModel
}