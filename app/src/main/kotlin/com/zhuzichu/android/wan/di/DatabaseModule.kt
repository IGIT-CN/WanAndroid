package com.zhuzichu.android.wan.di

import com.zhuzichu.android.wan.db.AppDatabase
import com.zhuzichu.android.wan.db.DaoUser
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(): AppDatabase = AppDatabase.getInstance()

    @Singleton
    @Provides
    fun providesDaoUser(appDatabase: AppDatabase): DaoUser = appDatabase.daoUser()

}