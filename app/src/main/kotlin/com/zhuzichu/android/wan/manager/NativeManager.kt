package com.zhuzichu.android.wan.manager

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
class NativeManager {

    init {
        System.loadLibrary("native-lib")
    }

    external fun gray(bitmap: Bitmap): Bitmap

}