package com.zhuzichu.android.wan.ui.demo.camera.main.viewmodel

import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.demo.camera.main.activity.ActivityCameraQRcode
import javax.inject.Inject

class ViewModelCamera @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickQRcodeFragment = createCommand {
        startFragment(R.id.action_fragmentCamera_to_fragmentCameraBasic)
    }

    val onClickQRcodeActivity = createCommand {
        startActivity(ActivityCameraQRcode::class.java)
    }

}