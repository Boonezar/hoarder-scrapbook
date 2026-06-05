package com.boonezar.hoarderscrapbook.models

import androidx.room.Embedded
import androidx.room.Relation

data class MemoryWithImages(
    @Embedded val memory: Memory,
    @Relation(
        parentColumn = "id",
        entityColumn = "memoryId"
    )
    val images: List<ImageUri>
)