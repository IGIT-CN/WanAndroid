package com.zhuzichu.android.wan.manager

import androidx.annotation.Keep

@Keep
class NativeManager {

    init {
        System.loadLibrary("native-lib")
    }

    external fun add(a: Int, b: Int): Int

    external fun sub(a: Int, b: Int): Int

    external fun mul(a: Int, b: Int): Int

    external fun div(a: Int, b: Int): Int
}