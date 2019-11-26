package com.zhuzichu.android.wan.ui.home.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.home.domain.UseCaseGetArticles
import me.tatarka.bindingcollectionadapter2.collections.AsyncDiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import javax.inject.Inject

class ViewModelHomeArticle @Inject constructor(
    private val useCaseGetArticles: UseCaseGetArticles
) : ViewModelAnalyticsBase() {

    private val pageHelper = PageHelper(
        DiffObservableList(itemDiffOf<ItemViewModelHomeArticle> { oldItem, newItem -> oldItem.id == newItem.id }),
        this,
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

    private fun loadArticles(page: Int) {
        useCaseGetArticles.execute(page)
            .autoDispose(this)
            .subscribe({
                pageHelper.put(it.data) {
                        ItemViewModelHomeArticle(this@ViewModelHomeArticle, this)
                }
            }, {
                pageHelper.showError()
                handleThrowable(it)
            })
    }
}