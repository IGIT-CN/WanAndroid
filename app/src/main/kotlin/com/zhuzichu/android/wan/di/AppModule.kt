package com.zhuzichu.android.wan.di

import android.content.Context
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.ApplicationWan
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: ApplicationWan): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesGlobalStorage(): GlobalStorage = GlobalStorage()

}