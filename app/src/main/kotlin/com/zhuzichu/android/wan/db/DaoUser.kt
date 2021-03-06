package com.zhuzichu.android.wan.db

import androidx.room.*
import com.zhuzichu.android.wan.db.entity.DOUser
import io.reactivex.Flowable

@Dao
interface DaoUser {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: DOUser)

    @Delete
    suspend fun deleteUser(list: List<DOUser>)

    @Query("select * from user order by loginTime desc")
    fun queryUsers(): Flowable<List<DOUser>>

}