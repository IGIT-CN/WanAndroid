package com.zhuzichu.android.wan.ui.demo.vxposed.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentVxposedBinding
import com.zhuzichu.android.wan.ui.demo.vxposed.main.viewmodel.ViewModelVxposed

class FragmentVxposed : FragmentAnalyticsBase<FragmentVxposedBinding, ViewModelVxposed>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_vxposed

}