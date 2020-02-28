package com.zhuzichu.android.wan.ui.home.viewmodel

import com.uber.autodispose.autoDispose
import com.zhuzichu.android.wan.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.ext.itemDiffOf
import com.zhuzichu.android.shared.ext.map
import com.zhuzichu.android.shared.widget.page.PageHelper
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseGetProjects
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import javax.inject.Inject

class ViewModelHomeProject @Inject constructor(
    private val useCaseGetProjects: UseCaseGetProjects,
    private val useCaseCollect: UseCaseCollect,
    private val useCaseUnCollect: UseCaseUnCollect
) : ViewModelAnalyticsBase() {

    private val pageHelper = PageHelper(
        this,
        DiffObservableList(itemDiffOf<ItemViewModelHomeProject>
        { oldItem, newItem ->
            oldItem.id == newItem.id
        }),
        onLoadMore = {
            loadProjects(this)
        }
    )

    val items: List<Any> = pageHelper.pageItems

    val onBottomCommand = pageHelper.onBottomCommand

    val onRefreshConmmand = pageHelper.onRefreshConmmand

    val itemBinding = pageHelper.itemBinding.apply {
        map<ItemViewModelHomeProject>(BR.item, R.layout.item_home_project)
    }

    private fun loadProjects(page: Int) {
        useCaseGetProjects.execute(page)
            .autoDispose(this)
            .subscribe({
                pageHelper.put(it.data) {
                    ItemViewModelHomeProject(
                        this@ViewModelHomeProject, this, useCaseCollect,
                        useCaseUnCollect
                    )
                }
            }, {
                pageHelper.showError()
                handleThrowable(it)
            })
    }

}