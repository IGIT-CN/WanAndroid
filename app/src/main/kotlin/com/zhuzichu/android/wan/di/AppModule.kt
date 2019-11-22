package com.zhuzichu.android.wan.di

import android.content.Context
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.ApplicationWan
import com.zhuzichu.android.wan.repository.RemoteRepository
import com.zhuzichu.android.wan.repository.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
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

    @Provides
    @Singleton
    fun providesRemoteRepository(
        @Named("gsonRetrofit") gsonRetrofit: Retrofit,
        @Named("htmlRetrofit") htmlRetrofit: Retrofit
    ): RemoteRepository {
        return RemoteRepositoryImpl(gsonRetrofit, htmlRetrofit)
    }
}