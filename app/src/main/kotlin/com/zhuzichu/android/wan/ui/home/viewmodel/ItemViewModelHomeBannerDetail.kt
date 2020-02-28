package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.createCommand
import com.zhuzichu.android.wan.repository.entity.BeanBanner
import com.zhuzichu.android.wan.ui.web.ActivityWeb

class ItemViewModelHomeBannerDetail(
    viewModel: BaseViewModel,
    bean: BeanBanner
) : ItemViewModelAnalyticsBase(viewModel) {

    val imagePath = MutableLiveData(bean.imagePath)

    val onClickItem = createCommand{
        startActivity(ActivityWeb::class.java, bundleOf(ActivityWeb.URL to bean.url))
    }

}