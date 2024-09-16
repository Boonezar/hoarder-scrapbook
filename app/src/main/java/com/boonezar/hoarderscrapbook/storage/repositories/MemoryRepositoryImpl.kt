package com.boonezar.hoarderscrapbook.storage.repositories

import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.models.MemoryDataWrapper
import com.boonezar.hoarderscrapbook.storage.memory_storage.MemoryMemoryStorage
import com.boonezar.hoarderscrapbook.storage.room_db.ImageUriDao
import com.boonezar.hoarderscrapbook.storage.room_db.MemoryDao
import javax.inject.Inject

class MemoryRepositoryImpl @Inject constructor(
    private val memoryMemoryStorage: MemoryMemoryStorage,
    private val memoryDao: MemoryDao,
    private val imageUriDao: ImageUriDao
): MemoryRepository {
    override fun getMemory(id: Int) = memoryDao.getById(id)
    override fun getMemories() = memoryDao.getAll()
    override suspend fun insertMemory(memory: Memory) = memoryDao.insert(memory).toInt()
    override suspend fun updateMemory(memory: Memory) = memoryDao.update(memory)
    override suspend fun deleteMemory(memory: Memory) = memoryDao.delete(memory)

    override fun getImage(id: Int) = imageUriDao.getById(id)
    override fun getImagesForMemory(memoryId: Int) = imageUriDao.getAllForMemory()
    override suspend fun insertImageUri(imageUri: ImageUri) = imageUriDao.insert(imageUri)
    override suspend fun deleteImageUri(imageUri: ImageUri) = imageUriDao.delete(imageUri)

    override fun getDataWrapper() = memoryMemoryStorage.get()
    override fun saveDataWrapper(data: MemoryDataWrapper) = memoryMemoryStorage.save(data)
    override fun clearDataWrapper() = memoryMemoryStorage.clear()
}