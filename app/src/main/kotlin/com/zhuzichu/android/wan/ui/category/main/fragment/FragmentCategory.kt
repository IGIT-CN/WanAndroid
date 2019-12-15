package com.zhuzichu.android.wan.ui.category.main.fragment

import androidx.lifecycle.Observer
import com.zhuzichu.android.libs.internal.MainHandler
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.databinding.FragmentCategoryBinding
import com.zhuzichu.android.wan.ui.category.main.viewmodel.ViewModelCategory
import kotlinx.android.synthetic.main.fragment_category.*

class FragmentCategory : FragmentAnalyticsBase<FragmentCategoryBinding, ViewModelCategory>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_category

    override fun initLazyData() {
        super.initLazyData()
        viewModel.showLoading()
        viewModel.updateTree()
    }

    override fun initViewObservable() {
        viewModel.currentIndex.observe(viewLifecycleOwner, Observer {
            recycler_satrt.post {
                recycler_satrt.smoothScrollToPosition(it)
            }
        })
    }

}