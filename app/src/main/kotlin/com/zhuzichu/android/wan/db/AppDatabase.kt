package com.zhuzichu.android.wan.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.db.entity.DOKeyword
import com.zhuzichu.android.wan.db.entity.DOUser

@Database(entities = [DOUser::class, DOKeyword::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoUser(): DaoUser

    abstract fun daoKeyword(): DaoKeyword

    companion object {
        private const val DATABASE_NAME = "db-wan"

        fun getInstance(): AppDatabase {
            return buildDatabase()
        }

        private fun buildDatabase(): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {

                })
                .build()
        }
    }
}
