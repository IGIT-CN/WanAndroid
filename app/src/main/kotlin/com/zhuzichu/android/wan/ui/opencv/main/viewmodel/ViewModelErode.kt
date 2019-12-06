package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.extension.logInvokeTime
import com.zhuzichu.android.wan.manager.OpencvManager
import javax.inject.Inject

class ViewModelErode @Inject constructor(
    private val opencvManager: OpencvManager
) : ViewModelAnalyticsBase() {

    private val src = BitmapFactory.decodeResource(context.resources, R.mipmap.guidao)

    var morph: Int = OpencvManager.MORPH_RECT

    var width = 17

    var height = 17

    val min = 3

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onClickRect = BindingCommand<Any>({
        logInvokeTime {
            morph = OpencvManager.MORPH_RECT
            updateBitmap()
        }
    })

    val onClickCross = BindingCommand<Any>({
        logInvokeTime {
            morph = OpencvManager.MORPH_CROSS
            updateBitmap()
        }
    })

    val onClickEllipse = BindingCommand<Any>({
        logInvokeTime {
            morph = OpencvManager.MORPH_ELLIPSE
            updateBitmap()
        }
    })

    val onSeekWidthCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            width = it + min
            updateBitmap()
        }
    })

    val onSeekHeightCommand = BindingCommand<Int>(consumer = {
        this?.let {
            if (it % 3 != 0)
                return@BindingCommand
            height = it + min
            updateBitmap()
        }
    })

    private fun updateBitmap() {
        bitmap.value =
            opencvManager.erode(src.copy(src.config, true), morph, width, height)
    }

}