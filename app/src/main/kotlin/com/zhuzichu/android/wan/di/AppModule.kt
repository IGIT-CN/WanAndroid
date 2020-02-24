package com.zhuzichu.android.wan.di

import android.content.Context
import com.zhuzichu.android.wan.ApplicationWan
import com.zhuzichu.android.wan.manager.FFmpegManager
import com.zhuzichu.android.wan.manager.JniDemoManager
import com.zhuzichu.android.wan.manager.OpencvManager
import com.zhuzichu.android.wan.manager.WebsocketManager
import com.zhuzichu.android.wan.repository.LocalRepository
import com.zhuzichu.android.wan.repository.LocalRepositoryImpl
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

    @Provides
    @Singleton
    fun providesRemoteRepository(
        @Named("gsonRetrofit") gsonRetrofit: Retrofit,
        @Named("htmlRetrofit") htmlRetrofit: Retrofit
    ): RemoteRepository {
        return RemoteRepositoryImpl(gsonRetrofit, htmlRetrofit)
    }

    @Provides
    @Singleton
    fun providesLocalRepository(): LocalRepository {
        return LocalRepositoryImpl()
    }

    @Provides
    @Singleton
    fun providesNativeManager(): OpencvManager {
        return OpencvManager()
    }

    @Provides
    @Singleton
    fun providesJniDemoManager(): JniDemoManager {
        return JniDemoManager()
    }

    @Provides
    @Singleton
    fun providesFFmpegManager(): FFmpegManager {
        return FFmpegManager()
    }

    @Provides
    @Singleton
    fun providesWebsocketManager(): WebsocketManager {
        return WebsocketManager()
    }

}