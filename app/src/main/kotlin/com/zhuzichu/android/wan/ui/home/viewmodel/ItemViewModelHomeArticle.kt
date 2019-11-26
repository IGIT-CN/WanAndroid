package com.zhuzichu.android.wan.ui.home.viewmodel

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.android.mvvm.base.BaseViewModel
import com.zhuzichu.android.mvvm.databinding.BindingCommand
import com.zhuzichu.android.shared.base.ItemViewModelAnalyticsBase
import com.zhuzichu.android.shared.extension.toColorByResId
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.repository.entity.BeanArticle

class ItemViewModelHomeArticle(
    viewModel: BaseViewModel,
    article: BeanArticle
) : ItemViewModelAnalyticsBase(viewModel) {
    val id = article.id
    val title = MutableLiveData(article.title)
    val description = MutableLiveData(article.desc)
    val user = MutableLiveData<String>().apply {
        value = if (!article.author.isNullOrBlank())
            "作者:".plus(article.author)
        else if (!article.shareUser.isNullOrBlank())
            "分享人:".plus(article.shareUser)
        else "神秘人物"
    }

    val collectColor = MutableLiveData<Int>().apply {
        value = if (article.collect == false)
            Color.parseColor("#4A4A4A")
        else
            R.color.color_primary.toColorByResId()
    }

    val onClickItem = BindingCommand<Any>({
        toast(id.toString())
    })
}