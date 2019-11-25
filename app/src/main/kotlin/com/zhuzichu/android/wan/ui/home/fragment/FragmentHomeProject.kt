package com.zhuzichu.android.wan.ui.home.fragment

import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentHomeArticleBinding
import com.zhuzichu.android.wan.ui.home.viewmodel.ViewModelHomeProject

class FragmentHomeProject : FragmentHomeChild<FragmentHomeArticleBinding, ViewModelHomeProject>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_home_project

}