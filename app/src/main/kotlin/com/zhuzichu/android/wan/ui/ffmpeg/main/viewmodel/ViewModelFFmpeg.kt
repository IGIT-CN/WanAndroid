package com.zhuzichu.android.wan.ui.ffmpeg.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelFFmpeg @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickGetInfo = BindingCommand<Any>({
        startFragment(R.id.action_fragmentFFmpeg_to_fragmentAVinfo)
    })

}