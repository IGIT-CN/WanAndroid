package com.zhuzichu.android.wan.ui.opencv.main.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
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
        toast("点击了")
    }

    val onClickReturn = createCommand {
        bitmap.value = src
    }
}