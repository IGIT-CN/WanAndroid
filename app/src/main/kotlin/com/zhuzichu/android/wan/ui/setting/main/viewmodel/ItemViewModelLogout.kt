package com.zhuzichu.android.wan.ui.setting.main.viewmodel

import com.zhuzichu.android.wan.ui.account.ActivityAccount
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand

class ItemViewModelLogout(viewModel: ViewModelSetting) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickLogout = createCommand {
        viewModel.globalStorage.cookies = null
        startActivity(ActivityAccount::class.java, isPop = true)
    }
}