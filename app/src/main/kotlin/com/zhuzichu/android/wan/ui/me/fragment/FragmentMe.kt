package com.zhuzichu.android.wan.ui.me.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.shared.extension.toast
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentMeBinding
import com.zhuzichu.android.wan.ui.me.viewmodel.ViewModelMe

class FragmentMe : FragmentAnalyticsBase<FragmentMeBinding, ViewModelMe>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_me

    override fun initLazyData() {
        super.initLazyData()
        viewModel.updateUserInfo()
    }
}