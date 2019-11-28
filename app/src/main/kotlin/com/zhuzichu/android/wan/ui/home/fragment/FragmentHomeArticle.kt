package com.zhuzichu.android.wan.ui.home.fragment

import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentHomeArticleBinding
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHomeArticle

class FragmentHomeArticle : FragmentHomeChild<FragmentHomeArticleBinding, ViewModelHomeArticle>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home_article

    override fun initFirstData() {
        viewModel.updateBanner()
        viewModel.updateTopArticles()
    }
}