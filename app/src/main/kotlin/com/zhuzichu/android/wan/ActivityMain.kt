package com.zhuzichu.android.wan

import android.os.Bundle
import com.lody.virtual.client.core.VirtualCore
import com.zhuzichu.android.shared.base.ActivityAnalyticsBase
import com.zhuzichu.android.shared.extension.toast

class ActivityMain : ActivityAnalyticsBase() {
    override fun setNavGraph(): Int = R.navigation.navigation_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doActionInThread()
    }

    private fun doActionInThread() {
        if (!VirtualCore.get().isEngineLaunched) {
            VirtualCore.get().waitForEngine()
        }
    }
}


