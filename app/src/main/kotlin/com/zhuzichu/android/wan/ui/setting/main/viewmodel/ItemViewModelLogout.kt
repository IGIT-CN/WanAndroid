package com.zhuzichu.android.wan.ui.setting.main.viewmodel

import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.shared.storage.GlobalStorage
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.ui.account.ActivityAccount

class ItemViewModelLogout(viewModel: ViewModelSetting) : ItemViewModelAnalyticsBase(viewModel) {

    val onClickLogout = createCommand {
        GlobalStorage.logout()
        startActivity(ActivityAccount::class.java, isPop = true)
    }
}