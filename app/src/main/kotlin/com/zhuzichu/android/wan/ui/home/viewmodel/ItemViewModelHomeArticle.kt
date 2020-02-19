package com.zhuzichu.android.wan.ui.home.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect

class ItemViewModelHomeArticle(
    viewModel: ViewModelAnalyticsBase,
    bean: BeanArticle,
    useCaseCollect: UseCaseCollect,
    useCaseUnCollect: UseCaseUnCollect
) : BaseItemViewModelAricle(viewModel, bean, useCaseCollect, useCaseUnCollect) {

    val topVisibility = MutableLiveData(View.GONE).apply {
        value = if (bean.type == 1) View.VISIBLE else View.GONE
    }
}
