package com.zhuzichu.android.wan.di

import com.zhuzichu.android.mvvm.di.ViewModelModule
import com.zhuzichu.android.wan.ApplicationWan
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<ApplicationWan> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: ApplicationWan): AppComponent
    }
}