package com.zhuzichu.android.wan.ui.search.list.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.itemDiffOf
import com.zhuzichu.android.shared.ext.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect
import com.zhuzichu.android.wan.ui.home.viewmodel.BaseItemViewModelAricle
import com.zhuzichu.android.wan.ui.home.viewmodel.ItemViewModelHomeArticle
import com.zhuzichu.android.wan.ui.home.viewmodel.ItemViewModelHomeProject
import com.zhuzichu.android.wan.ui.search.list.domain.UseCaseSearchArticle
import com.zhuzichu.android.wan.ui.search.main.entity.ParamterSearch
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import javax.inject.Inject

class ViewModelSearchList @Inject constructor(
    private val useCaseSearchArticle: UseCaseSearchArticle,
    private val useCaseCollect: UseCaseCollect,
    private val useCaseUnCollect: UseCaseUnCollect
) : ViewModelAnalyticsBase() {

    val keyword = MutableLiveData<String>()

    private val pageHelper = PageHelper(
        this,
        DiffObservableList(itemDiffOf<BaseItemViewModelAricle>
        { oldItem, newItem ->
            oldItem.id == newItem.id
        }),
        onLoadMore = {
            loadAricles(this)
        }
    )

    val items: List<Any> = pageHelper.pageItems

    val onBottomCommand = pageHelper.onBottomCommand

    val onRefreshConmmand = pageHelper.onRefreshConmmand

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelHomeProject>(BR.item, R.layout.item_home_project)
        map<ItemViewModelHomeArticle>(BR.item, R.layout.item_home_article)
    }

    private fun loadAricles(page: Int) {
        useCaseSearchArticle.execute(ParamterSearch(page, keyword.value.toString()))
            .autoDispose(this)
            .subscribe({
                pageHelper.put(it.data) {
                    if (superChapterId == 294)
                        ItemViewModelHomeProject(
                            this@ViewModelSearchList, this, useCaseCollect,
                            useCaseUnCollect
                        )
                    else
                        ItemViewModelHomeArticle(
                            this@ViewModelSearchList, this, useCaseCollect,
                            useCaseUnCollect
                        )
                }
            }, {
                pageHelper.showError()
                handleThrowable(it)
            })
    }

}