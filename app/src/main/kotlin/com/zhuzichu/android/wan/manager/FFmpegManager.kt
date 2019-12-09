package com.zhuzichu.android.wan.manager

class FFmpegManager {
    init {
        System.loadLibrary("ffmpeg-lib")
    }

    external fun getConfiguration(): String
}