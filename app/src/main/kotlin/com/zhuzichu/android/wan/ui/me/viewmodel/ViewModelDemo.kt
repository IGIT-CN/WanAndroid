package com.zhuzichu.android.wan.ui.me.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelDemo @Inject constructor(
) : ViewModelAnalyticsBase() {

    companion object {
        const val TYPE_OPENCV = 0
    }

    val items = MutableLiveData<List<Any>>().apply {
        value = listOf(
            ItemViewModelDemo(this@ViewModelDemo, TYPE_OPENCV, R.string.opencv)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelDemo>(BR.item, R.layout.item_demo)
    }
}