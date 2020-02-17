package com.zhuzichu.android.wan.manager

import android.graphics.Bitmap
import androidx.annotation.Keep

@Keep
class OpencvManager {

    companion object {
        const val MORPH_RECT = 0
        const val MORPH_CROSS = 1
        const val MORPH_ELLIPSE = 2
    }

    init {
        System.loadLibrary("opencv-lib")
    }

    external fun gray(bitmap: Bitmap): Bitmap

    external fun erode(bitmap: Bitmap, morph: Int, width: Int, height: Int): Bitmap

    external fun blur(bitmap: Bitmap, width: Int, height: Int): Bitmap

    external fun dilate(bitmap: Bitmap, morph: Int, width: Int, height: Int): Bitmap

    /**
     * @bitmap
     * @threshold1 低于这个阈值的像素点会被认为不是边缘
     * @threshold2 高于这个阈值的像素点会被认为是边缘
     * @apertureSize Sober算子大小
     * @isL2 是否采用更精确的方式计算图像梯度
     * 在阈值threshold1和阈值threshold2之间的像素点,若与第2步得到的边缘像素点相邻，则被认为是边缘，否则被认为不是边缘。
     */
    external fun canny(
        bitmap: Bitmap,
        threshold1: Double,
        threshold2: Double,
        apertureSize: Int,
        isL2: Boolean
    ): Bitmap
}