package com.zhuzichu.android.wan.ui.demo.jni.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand

class ItemViewModelJni(
    viewModel: BaseViewModel,
    val type: Int,
    string: String,
    closure: Int.() -> Unit
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(string)

    val onClickItem = createCommand {
        closure.invoke(type)
    }
}