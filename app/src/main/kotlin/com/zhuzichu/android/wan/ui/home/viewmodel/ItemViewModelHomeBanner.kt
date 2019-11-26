package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ItemViewModelHomeBanner(
    viewModel: BaseViewModel,
    val items: List<Any>
) : ItemViewModelAnalyticsBase(viewModel) {

   private val pagerSnapHelper by lazy {
        PagerSnapHelper()
    }

    val initRecycler = BindingCommand<RecyclerView>(consumer = {
        this?.let {
            pagerSnapHelper.attachToRecyclerView(it)
        }
    })


    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelBanner>(BR.item, R.layout.item_banner)
    }

}