package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.databinding.ObservableArrayList
import com.zhuzichu.android.wan.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.map
import com.zhuzichu.android.shared.widget.banner.BannerHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass


class ItemViewModelHomeBanner(
    viewModel: ViewModelAnalyticsBase
) : ItemViewModelAnalyticsBase(viewModel) {

    val items = ObservableArrayList<Any>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelHomeBannerDetail>(BR.item, R.layout.item_home_banner_detail)
    }

    private val bannerHelper = BannerHelper(
        viewModel,
        items
    )

    val recyclerCommand = bannerHelper.recyclerCommand

    fun update(list: List<Any>) = bannerHelper.update(list)

}