package com.zhuzichu.android.wan.ui.demo.opencv.main.fragment

import com.zhuzichu.android.wan.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentIdcardBinding
import com.zhuzichu.android.wan.ui.demo.opencv.main.viewmodel.ViewModelIdcard


class FragmentIdcard : FragmentAnalyticsBase<FragmentIdcardBinding, ViewModelIdcard>() {

    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_idcard

}