package com.zhuzichu.android.wan.di

import com.zhuzichu.android.mvvm.di.ActivityScoped
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.ui.account.login.module.ModuleLogin
import com.zhuzichu.android.wan.ui.category.module.ModuleCategory
import com.zhuzichu.android.wan.ui.ffmpeg.ActivityFFmpeg
import com.zhuzichu.android.wan.ui.ffmpeg.main.module.ModuleAVinfo
import com.zhuzichu.android.wan.ui.ffmpeg.main.module.ModuleFFmpeg
import com.zhuzichu.android.wan.ui.home.module.ModuleHome
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeArticle
import com.zhuzichu.android.wan.ui.home.module.ModuleHomeProject
import com.zhuzichu.android.wan.ui.jni.ActivityJni
import com.zhuzichu.android.wan.ui.jni.main.module.ModuleJni
import com.zhuzichu.android.wan.ui.main.module.ModuleMain
import com.zhuzichu.android.wan.ui.me.module.ModuleDemo
import com.zhuzichu.android.wan.ui.me.module.ModuleMe
import com.zhuzichu.android.wan.ui.opencv.ActivityOpencv
import com.zhuzichu.android.wan.ui.opencv.main.module.*
import com.zhuzichu.android.wan.ui.search.ActivitySearch
import com.zhuzichu.android.wan.ui.search.list.module.ModuleSearchList
import com.zhuzichu.android.wan.ui.search.main.module.ModuleSearch
import com.zhuzichu.android.wan.ui.setting.animation.module.ModuleAnimation
import com.zhuzichu.android.wan.ui.setting.languages.module.ModuleLanguages
import com.zhuzichu.android.wan.ui.setting.main.module.ModuleSetting
import com.zhuzichu.android.wan.ui.setting.theme.module.ModuleTheme
import com.zhuzichu.android.wan.ui.web.ActivityWeb
import com.zhuzichu.android.wan.ui.web.main.module.ModuleWeb
import com.zhuzichu.android.wan.ui.websocket.ActivityWebsocket
import com.zhuzichu.android.wan.ui.websocket.main.module.ModuleWebsocket
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
            ModuleCategory::class,
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
            ModuleBlur::class,
            ModuleCanny::class,
            ModuleIdcard::class
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

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleFFmpeg::class,
            ModuleAVinfo::class
        ]
    )
    internal abstract fun ffmpegActivity(): ActivityFFmpeg

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleWebsocket::class
        ]
    )
    internal abstract fun websocketActivity(): ActivityWebsocket


    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleWeb::class
        ]
    )
    internal abstract fun webActivity(): ActivityWeb

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleSearch::class,
            ModuleSearchList::class
        ]
    )
    internal abstract fun searchActivity(): ActivitySearch
}