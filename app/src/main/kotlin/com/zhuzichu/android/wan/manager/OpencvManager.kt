package com.zhuzichu.android.wan.manager

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
class OpencvManager {

    companion object{
        const val MORPH_RECT=0
        const val MORPH_CROSS=0
        const val MORPH_ELLIPSE=0
    }

    init {
        System.loadLibrary("opencv-lib")
    }

    external fun gray(bitmap: Bitmap): Bitmap

    external fun erode(bitmap: Bitmap, morph: Int, width: Int, height: Int): Bitmap

    external fun blur(bitmap: Bitmap, width: Int, height: Int): Bitmap
}