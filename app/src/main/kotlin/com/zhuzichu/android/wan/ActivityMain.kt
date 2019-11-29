package com.zhuzichu.android.wan

import android.content.Context
import android.os.Bundle
import com.zhuzichu.android.shared.base.ActivityAnalyticsBase
import com.zhuzichu.android.shared.extension.logi

class ActivityMain : ActivityAnalyticsBase() {
    override fun setNavGraph(): Int = R.navigation.navigation_main

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        "onAttachedToWindow".logi("哈哈哈")
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        "attachBaseContext".logi("哈哈哈")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        "onCreate".logi("哈哈哈")
    }
}


