package com.zhuzichu.android.wan.ui.search.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
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

}