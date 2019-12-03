package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase

class ItemViewModelDemo(
    viewModel: BaseViewModel,
    @StringRes stringId: Int
) : ItemViewModelAnalyticsBase(viewModel) {
    val title = MutableLiveData(stringId)
}