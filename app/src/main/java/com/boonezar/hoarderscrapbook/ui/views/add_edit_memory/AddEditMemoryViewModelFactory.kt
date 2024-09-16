package com.boonezar.hoarderscrapbook.ui.views.add_edit_memory

import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AddEditMemoryViewModelFactory {
    fun create(memoryRepository: MemoryRepository): AddEditMemoryViewModel
}