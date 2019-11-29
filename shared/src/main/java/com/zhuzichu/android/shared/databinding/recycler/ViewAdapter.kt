package com.zhuzichu.android.shared.databinding.recycler

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.rxbinding.scrollBottom
import com.zhuzichu.android.widget.recycler.BottomRecyclerView
import java.util.concurrent.TimeUnit

@BindingAdapter(value = ["onScrollBottom"], requireAll = false)
fun bindRecyclerView(
    recyclerView: BottomRecyclerView,
    onScrollBottom: BindingCommand<*>?
) {
    onScrollBottom?.apply {
        recyclerView.scrollBottom()
            .throttleFirst(100, TimeUnit.MILLISECONDS)
            .subscribe {
                execute()
            }
    }
}

@BindingAdapter(value = ["onRefresh"], requireAll = false)
fun bindSwipeRefreshLayout(
    swipeRefreshLayout: SwipeRefreshLayout,
    onRefresh: BindingCommand<SwipeRefreshLayout>?
) {
    swipeRefreshLayout.setOnRefreshListener {
        onRefresh?.execute(swipeRefreshLayout)
    }
}

