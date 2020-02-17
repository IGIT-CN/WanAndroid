package com.zhuzichu.android.wan.ui.demo.vxposed.loading.fragment

import android.graphics.Bitmap
import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.bindArgument
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentVirtualLoadingBinding
import com.zhuzichu.android.wan.ui.demo.vxposed.loading.viewmodel.ViewModelVirtualLoading

class FragmentVirtualLoading :
    FragmentAnalyticsBase<FragmentVirtualLoadingBinding, ViewModelVirtualLoading>() {

    val name: String? by bindArgument()

    val icon: Bitmap? by bindArgument()

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_virtual_loading

    override fun initView() {
        super.initView()
        viewModel.icon.value = icon
        viewModel.name.value = name
    }

}