package com.zhuzichu.android.wan.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.ui.account.login.module.ModuleLogin
import com.zhuzichu.android.wan.ui.home.module.ModuleHome
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeArticle
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeProject
import com.zhuzichu.android.wan.ui.jni.ActivityJni
import com.zhuzichu.android.wan.ui.jni.main.module.ModuleJni
import com.zhuzichu.android.wan.ui.main.module.ModuleMain
import com.zhuzichu.android.wan.ui.me.module.ModuleDemo
import com.zhuzichu.android.wan.ui.me.module.ModuleMe
import com.zhuzichu.android.wan.ui.opencv.ActivityOpencv
import com.zhuzichu.android.wan.ui.opencv.main.module.ModuleBlur
import com.zhuzichu.android.wan.ui.opencv.main.module.ModuleErode
import com.zhuzichu.android.wan.ui.opencv.main.module.ModuleGray
import com.zhuzichu.android.wan.ui.opencv.main.module.ModuleOpencv
import com.zhuzichu.android.wan.ui.search.module.ModuleSearch
import com.zhuzichu.android.wan.ui.setting.animation.module.ModuleAnimation
import com.zhuzichu.android.wan.ui.setting.languages.module.ModuleLanguages
import com.zhuzichu.android.wan.ui.setting.main.module.ModuleSetting
import com.zhuzichu.android.wan.ui.setting.theme.module.ModuleTheme
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleMain::class,
            ModuleHome::class,
            ModuleMe::class,
            ModuleHomeArticle::class,
            ModuleHomeProject::class,
            ModuleSetting::class,
            ModuleLanguages::class,
            ModuleTheme::class,
            ModuleAnimation::class,
            ModuleSearch::class,
            ModuleDemo::class
        ]
    )
    internal abstract fun mainActivity(): ActivityMain

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleLogin::class
        ]
    )
    internal abstract fun accountActivity(): ActivityAccount


    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleOpencv::class,
            ModuleGray::class,
            ModuleErode::class,
            ModuleBlur::class
        ]
    )
    internal abstract fun opencvActivity(): ActivityOpencv


    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleJni::class
        ]
    )
    internal abstract fun jniActivity(): ActivityJni
}