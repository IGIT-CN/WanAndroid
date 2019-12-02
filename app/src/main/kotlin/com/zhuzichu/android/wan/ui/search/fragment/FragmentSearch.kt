package com.zhuzichu.android.wan.ui.search.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentSearchBinding
import com.zhuzichu.android.wan.ui.search.viewmodel.ViewModelSearch

class FragmentSearch : FragmentAnalyticsBase<FragmentSearchBinding, ViewModelSearch>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_search
}