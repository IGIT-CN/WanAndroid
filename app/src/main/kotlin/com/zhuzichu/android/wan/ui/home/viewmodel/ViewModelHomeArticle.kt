package com.zhuzichu.android.wan.ui.home.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.home.domain.*
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import javax.inject.Inject

class ViewModelHomeArticle @Inject constructor(
    private val useCaseGetArticles: UseCaseGetArticles,
    private val useCaseGetBanner: UseCaseGetBanner,
    private val useCaseGetTopArticles: UseCaseGetTopArticles,
    private val useCaseCollect: UseCaseCollect,
    private val useCaseUnCollect: UseCaseUnCollect
) : ViewModelAnalyticsBase() {

    private val pageHelper = PageHelper(
        this,
        DiffObservableList(itemDiffOf<ItemViewModelHomeArticle>
        { oldItem, newItem ->
            oldItem.id == newItem.id
        }),
        onLoadMore = {
            loadArticles(this)
        },
        onRefresh = {
            updateBanner()
            updateTopArticles()
        },
        onRetry = {
            updateBanner()
            updateTopArticles()
        }
    )

    private val itemViewModelHomeBanner = ItemViewModelHomeBanner(this)

    private val itemsTop = DiffObservableList(itemDiffOf<ItemViewModelHomeArticle>
    { oldItem, newItem ->
        oldItem.id == newItem.id
    })

    val items: List<Any> = MergeObservableList<Any>()
        .insertItem(itemViewModelHomeBanner)
        .insertList(itemsTop)
        .insertList(pageHelper.pageItems)

    val onBottomCommand = pageHelper.onBottomCommand

    val onRefreshConmmand = pageHelper.onRefreshConmmand

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelHomeBanner>(BR.item, R.layout.item_home_banner)
        map<ItemViewModelHomeArticle>(BR.item, R.layout.item_home_article)
    }

    private fun loadArticles(page: Int) {
        useCaseGetArticles.execute(page)
            .autoDispose(this)
            .subscribe({
                pageHelper.put(it.data) {
                    ItemViewModelHomeArticle(
                        this@ViewModelHomeArticle, this, useCaseCollect,
                        useCaseUnCollect
                    )
                }
            }, {
                pageHelper.showError()
                handleThrowable(it)
            })
    }

    fun updateTopArticles() {
        useCaseGetTopArticles.execute(Unit)
            .autoDispose(this)
            .subscribe(
                {
                    it.data?.apply {
                        itemsTop.update(this.map { item ->
                            ItemViewModelHomeArticle(
                                this@ViewModelHomeArticle, item, useCaseCollect,
                                useCaseUnCollect
                            )
                        })
                    }
                },
                {
                    handleThrowable(it)
                }
            )
    }

    fun updateBanner() {
        useCaseGetBanner.execute(Unit)
            .autoDispose(this)
            .subscribe(
                {
                    it.data?.apply {
                        itemViewModelHomeBanner.update(this.map { item ->
                            ItemViewModelHomeBannerDetail(this@ViewModelHomeArticle, item)
                        })
                    }
                },
                {
                    handleThrowable(it)
                }
            )
    }

}