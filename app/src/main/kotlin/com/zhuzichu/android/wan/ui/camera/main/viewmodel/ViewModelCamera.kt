package com.zhuzichu.android.wan.ui.camera.main.viewmodel

import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelCamera @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickBasic = createCommand {
        startFragment(R.id.action_fragmentCamera_to_fragmentCameraBasic)
    }

}