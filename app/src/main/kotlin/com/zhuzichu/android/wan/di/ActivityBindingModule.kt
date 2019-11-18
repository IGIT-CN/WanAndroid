package com.zhuzichu.android.wan.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.ui.main.module.ModuleMain
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleMain::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

}