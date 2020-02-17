package com.zhuzichu.android.wan.manager

class FFmpegManager {
    init {
        System.loadLibrary("ffmpeg-lib")
    }

    external fun getConfigurationInfo(): String

    external fun getAvFilterInfo(): String

    external fun getAvCodecInfo(): String

    external fun getAvFormatInfo(): String

    external fun getUrlProtocolInfo(): String
}