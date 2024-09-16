package com.boonezar.hoarderscrapbook.di

import android.content.Context
import com.boonezar.hoarderscrapbook.storage.memory_storage.MemoryMemoryStorage
import com.boonezar.hoarderscrapbook.storage.memory_storage.MemoryMemoryStorageImpl
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepository
import com.boonezar.hoarderscrapbook.storage.repositories.MemoryRepositoryImpl
import com.boonezar.hoarderscrapbook.storage.room_db.ImageUriDao
import com.boonezar.hoarderscrapbook.storage.room_db.MemoryDao
import com.boonezar.hoarderscrapbook.storage.room_db.MemoryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object AppModule {
    @ViewModelScoped
    @Provides
    fun provideMemoryRepository(
        memoryMemoryStorage: MemoryMemoryStorage,
        memoryDao: MemoryDao,
        imageUriDao: ImageUriDao
    ): MemoryRepository = MemoryRepositoryImpl(memoryMemoryStorage, memoryDao, imageUriDao)

    @ViewModelScoped
    @Provides
    fun provideMemoryMemoryStorage(): MemoryMemoryStorage = MemoryMemoryStorageImpl()

    @ViewModelScoped
    @Provides
    fun provideMemoryDao(@ApplicationContext context: Context): MemoryDao =
        MemoryRoomDatabase.getInstance(context).memoryDao()

    @ViewModelScoped
    @Provides
    fun provideImageUriDao(@ApplicationContext context: Context): ImageUriDao =
        MemoryRoomDatabase.getInstance(context).imageUriDao()
}