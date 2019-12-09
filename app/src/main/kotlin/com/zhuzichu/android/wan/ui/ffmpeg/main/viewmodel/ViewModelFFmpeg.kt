package com.zhuzichu.android.wan.ui.ffmpeg.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.manager.FFmpegManager
import javax.inject.Inject

class ViewModelFFmpeg @Inject constructor(
    private val ffmpegManager: FFmpegManager
) : ViewModelAnalyticsBase() {
    val configuration = MutableLiveData<String>().apply {
        value = ffmpegManager.getConfiguration()
    }
}