package com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.OpencvManager
import javax.inject.Inject

class ViewModelGray @Inject constructor(
    private val opencvManager: OpencvManager
) : ViewModelAnalyticsBase() {

    private val src = BitmapFactory.decodeResource(context.resources, R.mipmap.guidao)

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onClickGray = createCommand {
        bitmap.value = opencvManager.gray(src.copy(src.config,true))
    }

    val onClickReturn = createCommand {
        bitmap.value = src
    }
}