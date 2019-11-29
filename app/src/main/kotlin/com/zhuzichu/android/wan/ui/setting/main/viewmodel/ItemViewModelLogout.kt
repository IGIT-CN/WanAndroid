package com.zhuzichu.android.wan.ui.setting.main.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelLogout(viewModel: ViewModelSetting) : ItemViewModelAnalyticsBase(viewModel) {
    val onClickLogout = BindingCommand<Any>({
        viewModel.globalStorage.cookies = null
        startActivity(ActivityAccount::class.java, isPop = true)
    })
}