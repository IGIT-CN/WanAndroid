package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.NativeManager
import javax.inject.Inject

class ViewModelGray @Inject constructor(
    private val nativeManager: NativeManager
) : ViewModelAnalyticsBase() {

    val src = BitmapFactory.decodeResource(context.resources, R.mipmap.guidao)

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onClickGray = BindingCommand<Any>({
        bitmap.value = nativeManager.gray(src)
    })

}