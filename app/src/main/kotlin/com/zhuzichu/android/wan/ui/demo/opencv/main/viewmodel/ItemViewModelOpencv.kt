package com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand

class ItemViewModelOpencv(
    viewModel: BaseViewModel,
    type: Int,
    @StringRes stringId: Int,
    closure: Int.() -> Unit
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(stringId)

    val onClickItem = createCommand {
        closure.invoke(type)
    }
}