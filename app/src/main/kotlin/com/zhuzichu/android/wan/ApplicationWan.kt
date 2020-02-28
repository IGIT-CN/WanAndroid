package com.zhuzichu.android.wan

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import com.lody.virtual.client.NativeEngine
import com.lody.virtual.client.core.VirtualCore
import com.lody.virtual.client.stub.VASettings
import com.umeng.commonsdk.UMConfigure
import com.zhuzichu.android.mvvm.MvvmManager
import com.zhuzichu.android.shared.crash.CrashConfig
import com.zhuzichu.android.shared.ext.className
import com.zhuzichu.android.shared.ext.loge
import com.zhuzichu.android.shared.ext.updateApplicationLanguage
import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.di.DaggerAppComponent
import com.zhuzichu.android.wan.ext.toAnimationBuild
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.vxposed.WanVirtualInitializer
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.flutter.view.FlutterMain
import io.reactivex.plugins.RxJavaPlugins
import jonathanfinerty.once.Once
import java.util.*

/**
 * Monkey测试 adb shell monkey -p com.zhuzichu.android.wan --ignore-crashes --ignore-timeouts  1000 >/c/Users/Administrator/Desktop/readme/log.txt 2>&1
 */
class ApplicationWan : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()

        AppGlobal.init(this).apply {
            loginClazz = ActivityAccount::class.java
        }

        Once.initialise(this)

        UMConfigure.init(
            this,
            BuildConfig.UMENG_APPKEY,
            BuildConfig.FLAVOR_CHANNEL,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )

        RxJavaPlugins.setErrorHandler {
            "Rxjava 拦截错误:".loge(className(), it)
            throw it
        }

        AppCompatDelegate.setDefaultNightMode(GlobalStorage.uiMode)
        updateApplicationLanguage(Locale(GlobalStorage.locale ?: Locale.getDefault().country))

        MvvmManager.animBuilder = GlobalStorage.animation.toAnimationBuild()

        FlutterMain.startInitialization(applicationContext)

        val virtualCore = VirtualCore.get()
        virtualCore.initialize(WanVirtualInitializer(virtualCore))

        CrashConfig.Builder.create().apply()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NativeEngine.disableJit(Build.VERSION.SDK_INT)
        }
        VASettings.ENABLE_IO_REDIRECT = true
        VASettings.ENABLE_INNER_SHORTCUT = false
        try {
            VirtualCore.get().startup(base)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}