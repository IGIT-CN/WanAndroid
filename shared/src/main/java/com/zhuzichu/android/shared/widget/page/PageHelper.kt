package com.zhuzichu.android.shared.widget.page

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.BR
import com.zhuzichu.android.shared.R
import com.zhuzichu.android.shared.base.ViewModelAnalyticsBase
import com.zhuzichu.android.shared.entity.BeanPage
import com.zhuzichu.android.shared.extension.map
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import java.lang.ref.WeakReference

class PageHelper(
    private val items: DiffObservableList<Any>,
    val viewModel: ViewModelAnalyticsBase,
    private var isFirstLoad: Boolean = true,
    private val onLoadMore: (Int.() -> Unit)? = null,
    private val onRefresh: (() -> Unit)? = null
) {
    var page = 0
    private var weakRefresh: WeakReference<SwipeRefreshLayout?>? = null

    private val onClickRetry = BindingCommand<Any>({
        onRefreshConmmand.execute()
    })

    private val networkViewModel = ItemViewModelNetwork(viewModel, onClickRetry)

    val pageItems: MergeObservableList<Any> = MergeObservableList<Any>()
        .insertItem(ItemViewModelNull(viewModel))
        .insertList(items)
        .insertItem(networkViewModel)

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ItemViewModelNetwork>(BR.item, R.layout.item_network)
        map<ItemViewModelNull>(BR.item, R.layout.item_null)
    }

    val onBottomCommand = BindingCommand<Any>({
        if (!isFirstLoad) {
            showDefalut()
            isFirstLoad = !isFirstLoad
            return@BindingCommand
        }
        if (getStatus() != ItemViewModelNetwork.STATE_LOADING) {
            showLoading()
            onLoadMore?.invoke(page)
        }
    })

    val onRefreshConmmand = BindingCommand<SwipeRefreshLayout>(consumer = {
        weakRefresh = WeakReference(this)
        if (getStatus() != ItemViewModelNetwork.STATE_LOADING) {
            page = 0
            onLoadMore?.invoke(page)
        } else {
            viewModel.toast("数据正在加载中")
            hideRefresh()
        }
        onRefresh?.invoke()
    })

    private fun hideRefresh() {
        weakRefresh?.get()?.isRefreshing = false
    }

    private fun showLoading() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_LOADING
    }

    fun showError() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_ERROR
        hideRefresh()
    }

    private fun showFinish() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_FINISH
    }

    private fun showEnd() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_END
    }

    private fun showDefalut() {
        networkViewModel.state.value = ItemViewModelNetwork.STATE_DEFAULT
    }

    private fun getStatus(): Int {
        return networkViewModel.state.value ?: ItemViewModelNetwork.STATE_LOADING
    }

    fun <T> put(beanPage: BeanPage<T>?, closure: T.() -> Any) {
        hideRefresh()
        val datas = beanPage?.datas
        if (datas.isNullOrEmpty()) {
            showEnd()
            return
        }
        val list = datas.map {
            closure.invoke(it)
        }
        if (beanPage.curPage == 1) {
            items.update(list)
        } else {
            items.update(items.plus(list))
        }
        MainHandler.postDelayed(Runnable {
            if (beanPage.curPage ?: 1 >= beanPage.pageCount ?: 1) {
                showEnd()
            } else {
                showFinish()
                page = page.inc()
            }
        }, 100)
    }
}