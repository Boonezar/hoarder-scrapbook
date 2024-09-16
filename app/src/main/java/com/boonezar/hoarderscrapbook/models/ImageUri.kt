package com.boonezar.hoarderscrapbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_uris")
data class ImageUri(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val memoryId: Int,
    val uri: String
)
