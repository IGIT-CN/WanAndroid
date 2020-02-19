package com.zhuzichu.android.wan.ui.search.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand

class ItemViewModelSearchKeyword(
    viewModel: ViewModelSearch,
    keyword: String,
    showClose: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val keyword = MutableLiveData(keyword)

    val showClose = MutableLiveData(showClose)

    val onClickCloase = createCommand {
        viewModel.dropHistoryKeyword(keyword)
    }

    val onClickItem = createCommand {
        viewModel.search(keyword)
    }
}