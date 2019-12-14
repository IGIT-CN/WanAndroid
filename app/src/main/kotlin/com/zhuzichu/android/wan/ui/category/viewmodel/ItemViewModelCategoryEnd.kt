package com.zhuzichu.android.wan.ui.category.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createCommand
import com.zhuzichu.android.wan.repository.entity.BeanNode
import com.zhuzichu.android.wan.ui.web.ActivityWeb

class ItemViewModelCategoryEnd(
    viewModel: BaseViewModel,
    val bean: BeanNode
) : ItemViewModelAnalyticsBase(viewModel) {

    val name = MutableLiveData(bean.name)

    val onClickItem = createCommand {
        startActivity(ActivityWeb::class.java, bundleOf(ActivityWeb.URL to "wwww.baidu.com"))
    }

}