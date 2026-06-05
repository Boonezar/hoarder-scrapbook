package com.boonezar.hoarderscrapbook.storage.repositories

import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.models.MemoryDataWrapper
import com.boonezar.hoarderscrapbook.models.MemoryWithImages
import kotlinx.coroutines.flow.Flow

interface MemoryRepository {
    fun getMemory(id: Int): Flow<Memory>
    fun getMemories(): Flow<List<Memory>>
    fun getAllMemoriesWithImages(): Flow<List<MemoryWithImages>>
    suspend fun insertMemory(memory: Memory): Int
    suspend fun updateMemory(memory: Memory)
    suspend fun deleteMemory(memory: Memory)
    fun getImage(id: Int): Flow<ImageUri>
    fun getImagesForMemory(memoryId: Int): Flow<List<ImageUri>>
    suspend fun insertImageUri(imageUri: ImageUri)
    suspend fun deleteImageUri(imageUri: ImageUri)
    fun getDataWrapper(): MemoryDataWrapper
    fun saveDataWrapper(data: MemoryDataWrapper)
    fun clearDataWrapper()
}