package com.zhuzichu.android.wan.ui.ffmpeg.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.manager.FFmpegManager
import javax.inject.Inject

class ViewModelAVinfo @Inject constructor(
    private val ffmpegManager: FFmpegManager
) : ViewModelAnalyticsBase() {
    val info = MutableLiveData<String>()

    val onClickConfiguration = BindingCommand<Any>({
        info.value = ffmpegManager.getConfigurationInfo()
    })

    val onClickProtocol = BindingCommand<Any>({
        info.value = ffmpegManager.getUrlProtocolInfo()
    })

    val onClickFormat = BindingCommand<Any>({
        info.value = ffmpegManager.getAvFormatInfo()
    })

    val onClickCodec = BindingCommand<Any>({
        info.value = ffmpegManager.getAvCodecInfo()
    })

    val onClickFilter = BindingCommand<Any>({
        info.value = ffmpegManager.getAvFilterInfo()
    })

}