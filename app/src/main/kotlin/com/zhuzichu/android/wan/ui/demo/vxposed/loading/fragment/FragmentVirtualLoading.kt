package com.zhuzichu.android.wan.ui.demo.vxposed.loading.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentVirtualLoadingBinding
import com.zhuzichu.android.wan.ui.demo.vxposed.loading.viewmodel.ViewModelVirtualLoading

class FragmentVirtualLoading :
    FragmentAnalyticsBase<FragmentVirtualLoadingBinding, ViewModelVirtualLoading>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_virtual_loading

}