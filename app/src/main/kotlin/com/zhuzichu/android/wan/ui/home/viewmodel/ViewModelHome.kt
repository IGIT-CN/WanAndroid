package com.zhuzichu.android.wan.ui.home.viewmodel

import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.R
import javax.inject.Inject

class ViewModelHome @Inject constructor() : ViewModelAnalyticsBase() {

    val onClickSearch = BindingCommand<Any>({
        startFragment(R.id.action_fragmentMain_to_fragmentSearch)
    })
}