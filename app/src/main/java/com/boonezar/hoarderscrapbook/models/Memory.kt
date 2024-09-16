package com.boonezar.hoarderscrapbook.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memories")
data class Memory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val entryDate: String,
    val estimateDateOfMemory: String,
    val name: String,
    val description: String
)
