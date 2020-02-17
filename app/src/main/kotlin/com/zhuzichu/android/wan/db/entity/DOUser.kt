package com.zhuzichu.android.wan.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "user",
    indices = [Index("id", "username")]
)
data class DOUser(
    @PrimaryKey
    var id: Long,
    var username: String,
    var cookies: String,
    var loginTime: Calendar? = Calendar.getInstance()
)