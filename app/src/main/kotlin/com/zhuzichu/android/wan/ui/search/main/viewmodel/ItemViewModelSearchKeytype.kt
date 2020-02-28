package com.zhuzichu.android.wan.ui.search.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList

class ItemViewModelSearchKeytype(
    viewModel: ViewModelSearch,
    title: String,
    val childs: AsyncDiffObservableList<Any>,
    showClean: Boolean
) : ItemViewModelAnalyticsBase(viewModel) {

    val title = MutableLiveData(title)

    val showClean = MutableLiveData(showClean)

    val onClickClean = createCommand {
        viewModel.dropAllHistoryKeyword()
    }

}