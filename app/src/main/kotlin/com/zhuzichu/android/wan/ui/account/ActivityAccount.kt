package com.zhuzichu.android.wan.ui.account

import android.os.Bundle
import com.zhuzichu.android.wan.ActivityMain
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.shared.base.ActivityAnalyticsBase
import com.zhuzichu.android.shared.storage.GlobalStorage
import javax.inject.Inject

class ActivityAccount : ActivityAnalyticsBase() {

    @Inject
    lateinit var globalStorage: GlobalStorage

    override fun setNavGraph(): Int = R.navigation.navigation_account

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalStorage.cookies?.let {
            startActivity(ActivityMain::class.java, isPop = true)
        }
    }

}