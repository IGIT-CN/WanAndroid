package com.zhuzichu.android.wan.ui.demo.vxposed.applist.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentAppListBinding
import com.zhuzichu.android.wan.ui.demo.vxposed.applist.viewmodel.ViewModelAppList

class FragmentAppList : FragmentAnalyticsBase<FragmentAppListBinding, ViewModelAppList>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_app_list

    override fun initFirstData() {
        super.initFirstData()
        viewModel.loadData()
    }

}