package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.repository.entity.BeanBanner

class ItemViewModelBanner(
    viewModel: BaseViewModel,
    beanBanner: BeanBanner
) : ItemViewModelAnalyticsBase(viewModel) {
    val imagePath = MutableLiveData(beanBanner.imagePath)
}