package com.boonezar.hoarderscrapbook.storage.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.boonezar.hoarderscrapbook.models.Memory
import com.boonezar.hoarderscrapbook.models.MemoryWithImages
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
    @Transaction
    @Query("SELECT * from memories WHERE id = :id")
    fun getByIdWithImages(id: Int): Flow<MemoryWithImages>
    @Query("SELECT * from memories ORDER BY entryDate DESC")
    fun getAll(): Flow<List<Memory>>
    @Transaction
    @Query("SELECT * from memories ORDER BY entryDate DESC")
    fun getAllWithImages(): Flow<List<MemoryWithImages>>
}