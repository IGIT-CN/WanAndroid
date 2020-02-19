package com.zhuzichu.android.wan.ui.category.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.repository.entity.BeanNode

class ItemViewModelCategoryStart(
    viewModel: BaseViewModel,
    index: Int,
    val bean: BeanNode,
    currentIndex: MutableLiveData<Int>,
    closure: (() -> Unit)? = null
) : ItemViewModelAnalyticsBase(viewModel) {

    val name = MutableLiveData(bean.name)

    val isSelected: LiveData<Boolean> = Transformations.map<Int, Boolean>(currentIndex) {
        index == it
    }

    val onClickItem = createCommand {
        closure?.invoke()
        currentIndex.value = index
    }

}