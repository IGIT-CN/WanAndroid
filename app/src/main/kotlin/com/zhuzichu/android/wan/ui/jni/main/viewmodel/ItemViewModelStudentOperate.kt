package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelStudentOperate(
    viewModel: ViewModelJni
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickPlus = BindingCommand<Any>({
        viewModel.plusSutdent()
    })

    val onClickMinus = BindingCommand<Any>({
        viewModel.dropStudent()
    })
}