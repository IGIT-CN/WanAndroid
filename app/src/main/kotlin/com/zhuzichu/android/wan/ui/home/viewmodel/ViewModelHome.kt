package com.zhuzichu.android.wan.ui.home.viewmodel

import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.wan.ui.search.ActivitySearch
import javax.inject.Inject

class ViewModelHome @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickSearch = createCommand {
        startActivity(ActivitySearch::class.java)
    }
}