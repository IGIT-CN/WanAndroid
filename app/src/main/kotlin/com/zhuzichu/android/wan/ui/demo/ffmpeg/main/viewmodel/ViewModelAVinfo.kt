package com.zhuzichu.android.wan.ui.demo.ffmpeg.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.wan.manager.FFmpegManager
import javax.inject.Inject

class ViewModelAVinfo @Inject constructor(
    private val ffmpegManager: FFmpegManager
) : ViewModelAnalyticsBase() {
    val info = MutableLiveData<String>()

    val onClickConfiguration = createCommand {
        info.value = ffmpegManager.getConfigurationInfo()
    }

    val onClickProtocol = createCommand {
        info.value = ffmpegManager.getUrlProtocolInfo()
    }

    val onClickFormat = createCommand {
        info.value = ffmpegManager.getAvFormatInfo()
    }

    val onClickCodec = createCommand {
        info.value = ffmpegManager.getAvCodecInfo()
    }

    val onClickFilter = createCommand {
        info.value = ffmpegManager.getAvFilterInfo()
    }

}