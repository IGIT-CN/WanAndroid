package com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.ext.toast
import com.zhuzichu.android.shared.global.AppGlobal.context
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.manager.OpencvManager
import javax.inject.Inject

class ViewModelIdcard @Inject constructor(
    private val opencvManager: OpencvManager
) : ViewModelAnalyticsBase() {

    private val src = BitmapFactory.decodeResource(context.resources, R.drawable.idcard)

    val bitmap = MutableLiveData<Bitmap>().apply {
        value = src
    }

    val onClickGet = createCommand {
        "点击了".toast()
    }

    val onClickReturn = createCommand {
        bitmap.value = src
    }
}