package com.boonezar.hoarderscrapbook.storage.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(memory: Memory): Long
    @Update
    suspend fun update(memory: Memory)
    @Delete
    suspend fun delete(memory: Memory)
    @Query("SELECT * from memories WHERE id = :id")
    fun getById(id: Int): Flow<Memory>
    @Query("SELECT * from memories JOIN image_uris ON memories.id = :id AND image_uris.memoryId = :id WHERE memories.id = :id")
    fun getByIdWithImages(id: Int): Flow<Any>
    @Query("SELECT * from memories ORDER BY entryDate DESC")
    fun getAll(): Flow<List<Memory>>
}