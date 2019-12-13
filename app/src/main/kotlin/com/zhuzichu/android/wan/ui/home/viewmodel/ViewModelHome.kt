package com.zhuzichu.android.wan.ui.home.viewmodel

import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelHome @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickSearch = createCommand{
        startFragment(R.id.action_fragmentMain_to_fragmentSearch)
    }
}