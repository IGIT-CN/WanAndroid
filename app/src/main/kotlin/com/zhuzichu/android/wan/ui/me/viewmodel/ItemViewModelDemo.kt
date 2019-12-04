package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.ui.jni.ActivityJni
import com.zhuzichu.android.wan.ui.opencv.ActivityOpencv

class ItemViewModelDemo(
    viewModel: BaseViewModel,
    type: Int,
    @StringRes stringId: Int
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(stringId)

    val onClickItem = BindingCommand<Any>({
        when (type) {
            ViewModelDemo.TYPE_OPENCV -> {
                startActivity(ActivityOpencv::class.java)
            }
            ViewModelDemo.TYPE_JNI -> {
                startActivity(ActivityJni::class.java)
            }
            else -> {
            }
        }
    })
}