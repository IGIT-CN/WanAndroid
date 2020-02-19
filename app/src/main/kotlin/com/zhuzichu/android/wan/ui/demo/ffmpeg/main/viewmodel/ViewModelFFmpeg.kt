package com.zhuzichu.android.wan.ui.demo.ffmpeg.main.viewmodel

import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelFFmpeg @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickGetInfo = createCommand {
        startFragment(R.id.action_fragmentFFmpeg_to_fragmentAVinfo)
    }

}