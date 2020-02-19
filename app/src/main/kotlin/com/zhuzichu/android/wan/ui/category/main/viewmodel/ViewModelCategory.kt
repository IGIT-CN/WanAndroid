package com.zhuzichu.android.wan.ui.category.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.libs.tool.doNotEmpty
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.createTypeCommand
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.category.main.domain.UseCaseGetTree
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject


class ViewModelCategory @Inject constructor(
    private val useCaseGetTree: UseCaseGetTree
) : ViewModelAnalyticsBase() {

    val currentIndex = MutableLiveData<Int>()

    val itemBindingStart = OnItemBindClass<Any>().apply {
        map<ItemViewModelCategoryStart>(BR.item, R.layout.item_category_start)
    }

    val itemsStart = MutableLiveData<List<Any>>()

    val onRefreshCommand = createTypeCommand<SwipeRefreshLayout> {
        updateTree(this)
    }

    val itemsEnd: LiveData<List<Any>> =
        Transformations.map<Int, List<Any>>(currentIndex) {
            (itemsStart.value?.get(it) as? ItemViewModelCategoryStart)?.bean?.let { parent ->
                parent.children?.mapIndexed { index, item ->
                    ItemViewModelCategoryEnd(
                        this,
                        item,
                        parent,
                        index
                    )
                }
            }
        }

    val itemBindingEnd = OnItemBindClass<Any>().apply {
        map<ItemViewModelCategoryEnd>(BR.item, R.layout.item_category_end)
    }

    fun updateTree(refresh: SwipeRefreshLayout? = null) {
        useCaseGetTree.execute(Unit)
            .doFinally {
                hideLoading()
                refresh?.isRefreshing = false
            }
            .autoDispose(this)
            .subscribe(
                {
                    doNotEmpty(it.data) {
                        itemsStart.value = mapIndexed { index, beanNode ->
                            ItemViewModelCategoryStart(
                                this@ViewModelCategory,
                                index,
                                beanNode,
                                currentIndex
                            )
                        }
                        currentIndex.value = 0
                    }
                },
                {
                    handleThrowable(it)
                }
            )
    }
}