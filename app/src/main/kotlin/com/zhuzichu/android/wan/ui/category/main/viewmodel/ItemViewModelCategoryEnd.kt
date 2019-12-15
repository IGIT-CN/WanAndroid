package com.zhuzichu.android.wan.ui.category.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.repository.entity.BeanNode
import com.zhuzichu.android.wan.ui.main.fragment.FragmentMainDirections

class ItemViewModelCategoryEnd(
    viewModel: BaseViewModel,
    bean: BeanNode,
    parent: BeanNode,
    index: Int
) : ItemViewModelAnalyticsBase(viewModel) {

    val name = MutableLiveData(bean.name)

    val onClickItem = createCommand {
        val directions =
            FragmentMainDirections.actionFragmentMainToFragmentCategoryList(parent, index)
        startFragment(directions)
    }

}