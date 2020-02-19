package com.zhuzichu.android.wan.ui.web

import com.zhuzichu.android.wan.base.ActivityAnalyticsBase
import com.zhuzichu.android.wan.R

class ActivityWeb : ActivityAnalyticsBase() {

    companion object {
        const val URL: String = "url"
    }

    override fun setNavGraph(): Int = R.navigation.navigation_web

}