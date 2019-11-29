package com.zhuzichu.android.wan

import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.di.DaggerAppComponent
import com.zhuzichu.android.wan.extension.toAnimationBuild
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber
import javax.inject.Inject

/**
 * Monkey测试 adb shell monkey -p com.zhuzichu.android.wan --ignore-crashes --ignore-timeouts  1000 >/c/Users/Administrator/Desktop/readme/log.txt 2>&1
 */
class ApplicationWan : DaggerApplication() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this).apply {
            loginClazz = ActivityAccount::class.java
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }
        AppCompatDelegate.setDefaultNightMode(globalStorage.uiMode)
        MvvmManager.animBuilder = globalStorage.animation.toAnimationBuild()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}