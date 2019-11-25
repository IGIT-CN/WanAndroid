package com.zhuzichu.android.wan.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.wan.repository.entity.BeanArticle

class ItemViewModelHomeArticle(
    viewModel: BaseViewModel,
    article: BeanArticle
) : ItemViewModelAnalyticsBase(viewModel) {
    val id = article.id
    val title = MutableLiveData(article.title)
    val description = MutableLiveData(article.desc)
    val user = MutableLiveData<String>().apply {
        value = if (article.userId == -1)
            "作者:".plus(article.author)
        else
            "分享人:".plus(article.shareUser)
    }
}