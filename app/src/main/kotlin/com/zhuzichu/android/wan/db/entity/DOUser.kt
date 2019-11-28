package com.zhuzichu.android.wan.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "user",
    indices = [Index("id", "username", "uid")]
)
data class DOUser(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var username: String,
    val loginTime: Calendar = Calendar.getInstance(),
    val cookies: String
)