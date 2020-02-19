package com.zhuzichu.android.wan.ui.category.list.fragment

import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentCategoryChildBinding
import com.zhuzichu.android.wan.repository.entity.BeanNode
import com.zhuzichu.android.wan.ui.category.list.viewmodel.ViewModelCategoryChild

class FragmentCategoryChild :
    FragmentAnalyticsBase<FragmentCategoryChildBinding, ViewModelCategoryChild>() {

    val beanNode: BeanNode? by bindArgument()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_category_child

    override fun initArgs() {
        viewModel.cid = beanNode?.id
    }

    override fun initFirstData() {
        super.initFirstData()
        viewModel.loadArticles(0)
    }

}