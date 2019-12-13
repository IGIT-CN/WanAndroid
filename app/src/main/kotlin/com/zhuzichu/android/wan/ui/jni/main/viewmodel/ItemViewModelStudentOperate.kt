package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand

class ItemViewModelStudentOperate(
    viewModel: ViewModelJni
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickPlus = createCommand {
        viewModel.plusSutdent()
    }

    val onClickMinus = createCommand {
        viewModel.dropStudent()
    }
}