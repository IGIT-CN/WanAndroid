package com.zhuzichu.android.wan

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.lody.virtual.client.NativeEngine
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.client.stub.VASettings
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.shared.extension.className
import com.zhuzichu.android.shared.extension.logi
import com.zhuzichu.android.shared.extension.updateApplicationLanguage
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.shared.widget.log.FileLoggingTree
import com.zhuzichu.android.wan.di.DaggerAppComponent
import com.zhuzichu.android.wan.extension.toAnimationBuild
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.vxposed.WanVirtualInitializer
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import jonathanfinerty.once.Once
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Monkey测试 adb shell monkey -p com.zhuzichu.android.wan --ignore-crashes --ignore-timeouts  1000 >/c/Users/Administrator/Desktop/readme/log.txt 2>&1
 */
class ApplicationWan : DaggerApplication() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun onCreate() {
        super.onCreate()

        Once.initialise(this)

        AppGlobal.init(this).apply {
            loginClazz = ActivityAccount::class.java
        }

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }

        AppCompatDelegate.setDefaultNightMode(globalStorage.uiMode)
        updateApplicationLanguage(Locale(globalStorage.locale ?: Locale.getDefault().country))

        MvvmManager.animBuilder = globalStorage.animation.toAnimationBuild()

        val virtualCore = VirtualCore.get()
        virtualCore.initialize(WanVirtualInitializer(this, virtualCore))
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun attachBaseContext(base: Context?) {
        initLog()
        super.attachBaseContext(base)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NativeEngine.disableJit(Build.VERSION.SDK_INT)
        }
        VASettings.ENABLE_IO_REDIRECT = true
        VASettings.ENABLE_INNER_SHORTCUT = false
        try {
            VirtualCore.get().startup(base)
        } catch (e: Throwable) {
            e.printStackTrace()
            "vexpoesd 启动失败".logi(className(), e)
        }
    }

    private fun initLog() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(FileLoggingTree())
        }
    }
}