package com.zhuzichu.android.wan.ui.jni.main.fragment

import com.zhuzichu.android.shared.base.FragmentAnalyticsBase
import com.zhuzichu.android.wan.BR
import com.zhuzichu.android.wan.R
import com.zhuzichu.android.wan.databinding.FragmentJniBinding
import com.zhuzichu.android.wan.ui.jni.main.viewmodel.ViewModelJni

class FragmentJni : FragmentAnalyticsBase<FragmentJniBinding, ViewModelJni>() {
    override fun bindVariableId(): Int = BR.viewModel

    override fun setLayoutId(): Int = R.layout.fragment_jni
}