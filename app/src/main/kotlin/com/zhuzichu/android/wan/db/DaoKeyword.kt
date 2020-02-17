package com.zhuzichu.android.wan.db

import androidx.room.*
import com.zhuzichu.android.wan.db.entity.DOKeyword
import io.reactivex.Flowable

@Dao
interface DaoKeyword {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(keyword: DOKeyword)

    @Delete
    suspend fun deleteKeyword(list: List<DOKeyword>)

    @Query("select * from history_keyword order by lastTime desc")
    fun queryKeywords(): Flowable<List<DOKeyword>>

}