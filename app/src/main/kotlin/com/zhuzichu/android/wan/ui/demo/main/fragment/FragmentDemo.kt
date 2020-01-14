package com.zhuzichu.android.wan.ui.demo.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentDemoBinding
import com.zhuzichu.android.wan.ui.me.viewmodel.ViewModelDemo

class FragmentDemo : FragmentAnalyticsBase<FragmentDemoBinding, ViewModelDemo>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_demo
}