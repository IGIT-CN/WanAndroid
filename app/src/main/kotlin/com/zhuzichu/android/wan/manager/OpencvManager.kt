package com.zhuzichu.android.wan.manager

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
class OpencvManager {

    init {
        System.loadLibrary("opencv-lib")
    }

    external fun gray(bitmap: Bitmap): Bitmap

    external fun erode(bitmap: Bitmap, morph: Int, width: Int, height: Int): Bitmap

    external fun blur(bitmap: Bitmap, width: Int, height: Int): Bitmap
}