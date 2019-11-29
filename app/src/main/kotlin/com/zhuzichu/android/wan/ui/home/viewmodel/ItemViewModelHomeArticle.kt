package com.zhuzichu.android.wan.ui.home.viewmodel

import android.graphics.Color
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDispose
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.autoLoading
import com.zhuzichu.android.shared.extension.toColorByResId
import com.zhuzichu.android.shared.extension.toStringByResId
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.repository.entity.BeanArticle
import com.zhuzichu.android.wan.ui.home.domain.UseCaseCollect
import com.zhuzichu.android.wan.ui.home.domain.UseCaseUnCollect

class ItemViewModelHomeArticle(
    val viewModel: ViewModelHomeArticle,
    private val article: BeanArticle,
    private val useCaseCollect: UseCaseCollect,
    private val useCaseUnCollect: UseCaseUnCollect
) : ItemViewModelAnalyticsBase(viewModel) {

    val id = article.id

    val title = MutableLiveData(article.title)

    val topVisibility = MutableLiveData(View.GONE).apply {
        value = if (article.type == 1) View.VISIBLE else View.GONE
    }

    val time = MutableLiveData(R.string.time.toStringByResId().plus(":").plus(article.niceDate))

    val user = MutableLiveData<String>().apply {
        value = if (!article.author.isNullOrBlank())
            R.string.author.toStringByResId().plus(":").plus(article.author)
        else if (!article.shareUser.isNullOrBlank())
            R.string.share_people.toStringByResId().plus(":").plus(article.shareUser)
        else R.string.anonymous.toStringByResId()
    }

    val collectColor = MutableLiveData<Int>()

    val onClickItem = BindingCommand<Any>({
    })

    val onClickCollect = BindingCommand<Any>({
        if (article.collect == true) {
            unCollect()
        } else {
            collect()
        }
    })

    init {
        updateCollect()
    }

    private fun updateCollect() {
        collectColor.value = if (article.collect == false)
            Color.parseColor("#4A4A4A")
        else
            R.color.color_primary.toColorByResId()
    }

    private fun collect() {
        if (id == null) {
            toast("not id")
            return
        }
        useCaseCollect.execute(id)
            .autoLoading(viewModel)
            .autoDispose(viewModel)
            .subscribe(
                {
                    article.collect = true
                    updateCollect()
                },
                {
                    viewModel.handleThrowable(it)
                }
            )
    }

    private fun unCollect() {
        if (id == null) {
            toast("not id")
            return
        }
        useCaseUnCollect.execute(id)
            .autoLoading(viewModel)
            .autoDispose(viewModel)
            .subscribe(
                {
                    article.collect = false
                    updateCollect()
                },
                {
                    viewModel.handleThrowable(it)
                }
            )
    }
}