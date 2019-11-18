package com.zhuzichu.android.wan.ui.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentMainBinding
import com.zhuzichu.android.wan.ui.main.viewmodel.ViewModelMain

class FragmentMain : FragmentAnalyticsBase<FragmentMainBinding, ViewModelMain>() {

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

}