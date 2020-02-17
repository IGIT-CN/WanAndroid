package com.zhuzichu.android.wan.ui.home.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.shared.extension.toStringByResId
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.repository.entity.BeanArticle

@BindingAdapter(value = ["bindArticleUser"], requireAll = false)
fun bindArticleUser(textView: TextView, article: BeanArticle) {
    val text = if (!article.author.isNullOrBlank())
        R.string.author.toStringByResId().plus(":").plus(article.author)
    else if (!article.shareUser.isNullOrBlank())
        R.string.share_people.toStringByResId().plus(":").plus(article.shareUser)
    else R.string.anonymous.toStringByResId()
    textView.text = text
}

@BindingAdapter(value = ["bindArticleTime"], requireAll = false)
fun bindArticleTime(textView: TextView, article: BeanArticle) {
    val text = R.string.time.toStringByResId().plus(":").plus(article.niceDate)
    textView.text = text
}

