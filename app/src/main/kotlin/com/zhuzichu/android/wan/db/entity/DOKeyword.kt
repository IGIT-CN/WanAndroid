package com.zhuzichu.android.wan.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "history_keyword",
    indices = [Index("keyword")]
)
data class DOKeyword(
    @PrimaryKey
    var keyword: String,
    var lastTime: Calendar = Calendar.getInstance()
)