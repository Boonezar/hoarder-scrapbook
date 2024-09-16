package com.boonezar.hoarderscrapbook.storage.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boonezar.hoarderscrapbook.models.ImageUri
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageUriDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(imageUri: ImageUri)
    @Delete
    suspend fun delete(imageUri: ImageUri)
    @Query("SELECT * from image_uris WHERE id = :id")
    fun getById(id: Int): Flow<ImageUri>
    @Query("SELECT * from image_uris")
    fun getAllForMemory(): Flow<List<ImageUri>>
}