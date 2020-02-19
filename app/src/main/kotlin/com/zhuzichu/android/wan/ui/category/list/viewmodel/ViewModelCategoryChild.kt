package com.zhuzichu.android.wan.ui.category.list.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.libs.tool.toInt
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.itemDiffOf
import com.zhuzichu.android.shared.extension.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.category.list.domain.UseCaseGetArticlesByCid
import com.zhuzichu.android.wan.ui.category.list.entity.ParamterCategory
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect
import com.zhuzichu.android.wan.ui.home.viewmodel.BaseItemViewModelAricle
import com.zhuzichu.android.wan.ui.home.viewmodel.ItemViewModelHomeArticle
import com.zhuzichu.android.wan.ui.home.viewmodel.ItemViewModelHomeProject
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import javax.inject.Inject

class ViewModelCategoryChild @Inject constructor(
    private val useCaseGetArticlesByCid: UseCaseGetArticlesByCid,
    private val useCaseCollect: UseCaseCollect,
    private val useCaseUnCollect: UseCaseUnCollect
) : ViewModelAnalyticsBase() {

    var cid: Int? = null

    private val pageHelper = PageHelper(
        this,
        DiffObservableList(itemDiffOf<BaseItemViewModelAricle>
        { oldItem, newItem ->
            oldItem.id == newItem.id
        }),
        onLoadMore = {
            loadArticles(this)
        }
    )

    val items: List<Any> = pageHelper.pageItems

    val onBottomCommand = pageHelper.onBottomCommand

    val onRefreshConmmand = pageHelper.onRefreshConmmand

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelHomeProject>(BR.item, R.layout.item_home_project)
        map<ItemViewModelHomeArticle>(BR.item, R.layout.item_home_article)
    }

    fun loadArticles(page: Int) {
        useCaseGetArticlesByCid.execute(ParamterCategory(page, toInt(cid)))
            .autoDispose(this)
            .subscribe(
                {
                    pageHelper.put(it.data) {
                        if (superChapterId == 294)
                            ItemViewModelHomeProject(
                                this@ViewModelCategoryChild, this, useCaseCollect,
                                useCaseUnCollect
                            )
                        else
                            ItemViewModelHomeArticle(
                                this@ViewModelCategoryChild, this, useCaseCollect,
                                useCaseUnCollect
                            )
                    }
                },
                {
                    handleThrowable(it)
                }
            )
    }

}