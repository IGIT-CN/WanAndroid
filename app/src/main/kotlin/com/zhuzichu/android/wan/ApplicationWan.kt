package com.zhuzichu.android.wan

import com.zhuzichu.android.shared.global.AppGlobal
import com.zhuzichu.android.wan.di.DaggerAppComponent
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class ApplicationWan : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this).apply {
            loginClazz = ActivityAccount::class.java
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}