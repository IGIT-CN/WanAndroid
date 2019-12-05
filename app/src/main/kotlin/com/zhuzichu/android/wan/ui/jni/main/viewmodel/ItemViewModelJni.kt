package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelJni(
    viewModel: BaseViewModel,
    val type: Int,
    string: String,
    closure: Int.() -> Unit
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(string)

    val onClickItem = BindingCommand<Any>({
        closure.invoke(type)
    })
}