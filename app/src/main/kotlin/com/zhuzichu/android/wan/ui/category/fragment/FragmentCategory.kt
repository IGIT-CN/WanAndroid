package com.zhuzichu.android.wan.ui.category.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.databinding.FragmentCategoryBinding
import com.zhuzichu.android.wan.ui.category.viewmodel.ViewModelCategory

class FragmentCategory : FragmentAnalyticsBase<FragmentCategoryBinding, ViewModelCategory>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_category

}