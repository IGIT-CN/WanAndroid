package com.zhuzichu.android.wan.ui.home.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.home.domain.UseCaseGetArticles
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import javax.inject.Inject

class ViewModelHomeArticle @Inject constructor(
    private val useCaseGetArticles: UseCaseGetArticles
) : ViewModelAnalyticsBase() {

    private val pageSize = 20

    private val pageHelper = PageHelper(
        AsyncDiffObservableList(itemDiffOf<ItemViewModelHomeArticle> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
        pageSize,
        onLoadMore = {
            loadArticles(it)
        }
    )

    val items = pageHelper.pageItems

    val onScrollBottom = pageHelper.onScrollBottom

    val onRefresh = pageHelper.onRefresh

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelHomeArticle>(BR.item, R.layout.item_home_article)
    }

    val onClickSearch = BindingCommand<Any>({

    })

    fun loadArticles(page: Int) {
        useCaseGetArticles.execute(page)
            .autoDispose(this)
            .subscribe({
                pageHelper.addAll(
                    it.data?.datas?.map { item ->
                        ItemViewModelHomeArticle(this, item)
                    }
                )
            }, {
                handleThrowable(it)
            })
    }

}