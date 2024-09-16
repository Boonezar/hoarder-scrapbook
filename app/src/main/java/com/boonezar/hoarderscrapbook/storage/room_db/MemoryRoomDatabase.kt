package com.boonezar.hoarderscrapbook.storage.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boonezar.hoarderscrapbook.models.ImageUri
import com.boonezar.hoarderscrapbook.models.Memory

@Database(entities = [(Memory::class), (ImageUri::class)], version = 2, exportSchema = false)
abstract class MemoryRoomDatabase : RoomDatabase() {
    abstract fun memoryDao(): MemoryDao
    abstract fun imageUriDao(): ImageUriDao

    companion object {
        @Volatile
        private var instance: MemoryRoomDatabase? = null

        fun getInstance(context: Context): MemoryRoomDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = MemoryRoomDatabase::class.java,
                    name = "memory_database"
                )
                .fallbackToDestructiveMigration()
                .build().also { instance = it }
            }
        }
    }
}