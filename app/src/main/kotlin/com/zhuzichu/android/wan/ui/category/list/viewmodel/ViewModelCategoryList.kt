package com.zhuzichu.android.wan.ui.category.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.repository.entity.BeanNode
import com.zhuzichu.android.wan.ui.category.main.viewmodel.ItemViewModelCategoryStart
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelCategoryList @Inject constructor() : ViewModelAnalyticsBase() {

    lateinit var beanNode: BeanNode

    val title = MutableLiveData<String>()

    val currentIndex = MutableLiveData<Int>()

    var lastIndex: Int? = null

    val itemBindingStart = OnItemBindClass<Any>().apply {
        map<ItemViewModelCategoryStart>(BR.item, R.layout.item_category_start)
    }

    val itemsStart = MutableLiveData<List<Any>>()

    fun updateStart(position: Int) {
        itemsStart.value = beanNode.children?.mapIndexed { index, beanNode ->
            ItemViewModelCategoryStart(this, index, beanNode, currentIndex) {
                lastIndex = currentIndex.value
            }
        }
        currentIndex.value = position
    }
}