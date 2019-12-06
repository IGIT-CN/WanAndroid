package com.zhuzichu.android.wan.ui.jni.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.manager.JniDemoManager

class ItemViewModelStudentOperate(
    viewModel: ViewModelJni,
    jniDemoManager: JniDemoManager
) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickPlus = BindingCommand<Any>({
        viewModel.plusSutdent(jniDemoManager.getStudent())
    })

    val onClickMinus = BindingCommand<Any>({
        viewModel.dropStudent()
    })
}