package com.zhuzichu.android.wan.ui.home.viewmodel

import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.ui.search.ActivitySearch
import javax.inject.Inject

class ViewModelHome @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickSearch = createCommand {
        startActivity(ActivitySearch::class.java)
    }
}