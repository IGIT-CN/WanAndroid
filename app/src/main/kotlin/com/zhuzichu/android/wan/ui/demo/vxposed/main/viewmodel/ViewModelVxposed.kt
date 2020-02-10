package com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel

import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelVxposed @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickCreateApp = createCommand {
        startFragment(R.id.action_fragmentVxposed_to_fragmentAppList)
    }

}